package com.reshui.account;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 账户模块
 * 
 * @author reshui
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ReshuiAccountApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReshuiAccountApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  账户模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
