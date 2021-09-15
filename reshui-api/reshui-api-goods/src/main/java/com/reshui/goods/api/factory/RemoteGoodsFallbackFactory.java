package com.reshui.goods.api.factory;



import com.reshui.common.core.domain.R;
import com.reshui.goods.api.RemoteGoodsService;
import com.reshui.goods.api.domain.Goods;
import com.reshui.goods.api.domain.GoodsFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 账户服务降级处理
 * 
 * @author ruoyi
 */
@Component
@Slf4j
public class RemoteGoodsFallbackFactory implements FallbackFactory<RemoteGoodsService>
{


    @Override
    public RemoteGoodsService create(Throwable throwable)
    {
        log.error("商品服务调用失败:{}", throwable.getMessage());
        return new RemoteGoodsService()
        {
            @Override
            public R<?> lock(String goodsId, Long num, String orderId, String userId) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }

            //@Override
            public R<?> getById(String id) {
                return R.fail("获取信息失败:" + throwable.getMessage());
            }

            @Override
            public R<?> updateById(Goods goods) {
                return R.fail("更新失败:" + throwable.getMessage());
            }

            @Override
            public R<?> add(GoodsFlow goodsFlow) {
                return R.fail("添加失败:" + throwable.getMessage());
            }

        };
    }
}
