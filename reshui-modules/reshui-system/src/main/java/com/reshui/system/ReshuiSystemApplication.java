package com.reshui.system;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统模块
 * 
 * @author reshui
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ReshuiSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReshuiSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
