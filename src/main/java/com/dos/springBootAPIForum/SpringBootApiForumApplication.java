package com.dos.springBootAPIForum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SpringBootApiForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiForumApplication.class, args);
	}

}
