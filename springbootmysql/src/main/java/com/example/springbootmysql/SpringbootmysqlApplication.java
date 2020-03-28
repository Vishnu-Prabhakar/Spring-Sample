package com.example.springbootmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootmysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmysqlApplication.class, args);
	}

}
