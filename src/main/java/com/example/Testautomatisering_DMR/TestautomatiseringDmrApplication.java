package com.example.Testautomatisering_DMR;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestautomatiseringDmrApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestautomatiseringDmrApplication.class, args);

		//SpringApplicationBuilder builder = new SpringApplicationBuilder(TestautomatiseringDmrApplication.class);

		//builder.headless(true);

		//ConfigurableApplicationContext context = builder.run(args);
	}

}
