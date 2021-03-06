package com.reshui.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单模块
 * 
 * @author reshui
 */
@EnableFeignClients(basePackages = "com.reshui")
@EnableDiscoveryClient
@SpringBootApplication
public class ReshuiOrderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReshuiOrderApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ 订单模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
