package com.pafacebank.facebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy()
public class FacebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebankApplication.class, args);
	}
}
