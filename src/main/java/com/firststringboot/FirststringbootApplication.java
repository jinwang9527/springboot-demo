package com.firststringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRabbit
@MapperScan("com.firststringboot.repository.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class FirststringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirststringbootApplication.class, args);
    }

}
