package com.example.PizzaRep;

import com.example.PizzaRep.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("/business/model")
@SpringBootApplication
public class PizzaRepApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaRepApplication.class, args);
	}

	@Autowired
	private PizzaService pizzaService;

	/*
	@Bean
	InitializingBean sendDatabase() {
		return () -> {


			pizzaService.add("Bianca");
			pizzaService.add("Margarita", new Ingredient("Sos pomidorowy"), new Ingredient("Ser"));
		};
	}
	*/


}
