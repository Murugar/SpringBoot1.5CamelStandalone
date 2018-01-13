package com.iqmsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:camel-route-spring.xml")
public class MagicRouterApp {

	public static void main(String[] args) {
		SpringApplication.run(MagicRouterApp.class, args);
	}
}