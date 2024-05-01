package com.example.lombard;

import com.example.lombard.api.controllers.UserController;
import com.example.lombard.api.facade.UserFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LombardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LombardApplication.class, args);
	}

}
