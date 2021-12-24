package com.reshui.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reshui.account.api.RemoteAccountService;
import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.order.entity.Order;
import com.reshui.order.service.IOrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reshui
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService iOrderService;

    @Autowired
    private RemoteAccountService remoteAccountService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 下单
     * @return
     */

    @PostMapping("/submit")
    public R<?> submit(String goodsId,Long num){

        Order order = new Order();
        order.setUserId("user1");
        order.setCreateTime(LocalDateTime.now());
        order.setGoodsId(goodsId);
        order.setGoodsNum(num.intValue());
        order.setOrderStatus("订单创建");
        //支付状态
        order.setStep("N");

        //提交订单 未支付
        boolean submitIsSuccess = iOrderService.submit("user1", order);

        return R.ok(submitIsSuccess);

    }
    /**
     * 取消订单
     */
    @PutMapping("/cancel/{orderId}")
    public R<String> cancelOrder(@PathVariable("orderId") String orderId) {
        String userId = "user1";
        LambdaQueryWrapper<Order> wrapper = new QueryWrapper<Order>().lambda()
                .eq(Order::getOrderId, orderId)
                .eq(Order::getUserId, userId);
        Order order = iOrderService.getOne(wrapper);
        if (order.getOrderStatus() == "订单支付") {
            // 订单已支付，无法取消订单
            return R.fail("订单已支付，不可取消");
        }
        // 如果订单未支付的话，将订单设为取消状态
        iOrderService.cancelOrder(order.getOrderId());
        return R.ok();
    }

    @PostMapping("/create")
    public R<?> create(Order order){
        //防止订单重复-redis
        //update goods set num = num-5 where goods_id = 1 and num>=5
        //
        //update goods set num = num-6 where goods_id = 1 and num>=6

        //获取当前用户信息

        //查询当前商品库存

        //扣减库存

        //生成商品库存流水

        //生成订单信息

        //to do (延时队列？)30分钟内需要付款，否则订单取消


        boolean save = iOrderService.save(order);

        return R.ok(save);

    }

    /**
     * 付款
     * @param id
     * @return
     */
    @PostMapping("/pay")
    public R<?> pay(String id){
        //查询当前用户信息

        //查询账户余额信息

        //扣减账户余额

        //生成账户流水

        //更新订单状态-已支付

        //生成即时消息通知后续服务处理




        return R.ok();

    }



    /**
     * 用户手动取消订单
     * @param id
     * @return
     */
    @PostMapping("/user/cancel")
    public R<?> cancel(String id){
        //判断订单状态

        //分为已经支付和未支付

        //未支付，回退库存-较简单

        //已支付，需要审核 - 较复杂

        //更新订单处理状态 为 取消订单处理中

        //生成消息入队通知后续服务处理


        return R.ok();

    }
    /**
     * 获取指定id订单详细信息-订单状态
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public R<?> getById(@PathVariable String id){

        Order byId = iOrderService.getById(id);

        return R.ok(byId);

    }

    /**
     * 更新订单信息
     * @param order
     * @return
     */
    @PostMapping("/update")
    public R<?> update(@PathVariable Order order){

        boolean b = iOrderService.updateById(order);

        return R.ok(b);

    }



//    @GetMapping("/get/{userId}")
//    public AjaxResult getAccount(@PathVariable String userId){
//
//        return AjaxResult.success(remoteAccountService.getAccountInfo(userId));
//
//    }

    @GetMapping("/list")
    public AjaxResult getList(){

        return AjaxResult.success(iOrderService.list());

    }

    @GetMapping("/mq")
    public R<?> mq(){

        rocketMQTemplate.convertAndSend("order-topic", "order-string-test");

        return R.ok();

    }
}

