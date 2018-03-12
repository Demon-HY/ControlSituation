package com.control.situation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Demon-Coffee on 2018/1/18 0018.
 */
@SpringBootApplication
@EnableAutoConfiguration
// mybatis
@MapperScan("com.control.situation.mapper")
// scheduling
@EnableAsync
// 配置事务
@EnableTransactionManagement
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
