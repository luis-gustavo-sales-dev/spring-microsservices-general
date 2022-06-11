package br.dev.luisgustavosales.generatepassword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GeneratePasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratePasswordApplication.class, args);
	}

}
