package com.reshui.order.controller;


import com.reshui.account.api.RemoteAccountService;
import com.reshui.common.core.domain.R;
import com.reshui.common.core.web.domain.AjaxResult;
import com.reshui.order.entity.Order;
import com.reshui.order.service.IOrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * @param order
     * @return
     */

    @PostMapping("/create")
    public R<?> create(Order order){
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



    @GetMapping("/get/{userId}")
    public AjaxResult getAccount(@PathVariable String userId){

        return AjaxResult.success(remoteAccountService.getAccountInfo(userId));

    }

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

