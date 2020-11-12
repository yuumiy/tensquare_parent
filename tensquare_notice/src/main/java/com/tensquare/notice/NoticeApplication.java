package com.tensquare.notice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
//开启Feign，用于其他微服务的调用
@EnableFeignClients
@MapperScan("com.tensquare.notice.dao")
public class NoticeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoticeApplication.class, args);
    }

    @Bean
    public IdWorker createIdWorker() {
        return new IdWorker(1, 1);
    }

}
