package com.fykj.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fykj"})
@MapperScan({"com.fykj.scaffold.security.business.mapper","com.fykj.scaffold.weixin.mp.mapper","com.fykj.scaffold.evaluation.mapper","com.fykj.scaffold.cms.mapper"})
public class ScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
