package com.findmy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrationAndLoginApplication  implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RegistrationAndLoginApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Project is up");
	}
}
