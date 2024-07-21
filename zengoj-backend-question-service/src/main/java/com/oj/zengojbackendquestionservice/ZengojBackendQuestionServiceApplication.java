package com.oj.zengojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *

 */
// todo 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication
@MapperScan("com.oj.zengojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
// 这里加上 @ComponentScan 注解，因为不是单体项目了，如果想要使用全局异常处理器，就必须加上扫描
@ComponentScan(basePackages = "com.oj")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.oj.zengojbackendserviceclient.service"})
public class ZengojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZengojBackendQuestionServiceApplication.class, args);
    }

}
