package com.dostavahrane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DostavaHraneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DostavaHraneApplication.class, args);
	}

}