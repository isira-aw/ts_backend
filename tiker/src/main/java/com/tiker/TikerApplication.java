package com.tiker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tiker"})
public class TikerApplication {
	public static void main(String[] args) {
		SpringApplication.run(TikerApplication.class, args);
	}
}
