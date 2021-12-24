package com.reshui.goods;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品模块
 * 
 * @author reshui
 */
@EnableFeignClients(basePackages = "com.reshui")
@EnableDiscoveryClient
@SpringBootApplication
public class ReshuiGoodsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReshuiGoodsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  商品模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
