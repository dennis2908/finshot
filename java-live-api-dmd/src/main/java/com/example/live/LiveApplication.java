package com.example.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiveApplication {

	public static void main(String[] args) {
		System.out.println("Hello guys!");
		SpringApplication.run(LiveApplication.class, args);
	}

}
