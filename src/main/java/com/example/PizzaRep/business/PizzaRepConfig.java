package com.example.PizzaRep.business;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.example.PizzaRep.business")
@EntityScan

public class PizzaRepConfig {

    public PizzaRepConfig(){
        super();
    }

}
