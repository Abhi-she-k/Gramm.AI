package com.example.Gramm.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		var welcomemess = new Welcome();
		System.out.println(welcomemess.sayHello());

	}

}
