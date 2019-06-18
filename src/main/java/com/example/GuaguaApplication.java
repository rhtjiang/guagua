package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration
@MapperScan("com.example.mapper.PartialMapper")
public class GuaguaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuaguaApplication.class, args);
    }

}
