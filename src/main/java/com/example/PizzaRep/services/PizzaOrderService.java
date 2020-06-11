package com.example.PizzaRep.services;

import com.example.PizzaRep.business.model.PizzaOrder;
import com.example.PizzaRep.business.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaOrderService {

    @Autowired
    PizzaOrderRepository pizzaOrderRepository;

    public void add(PizzaOrder pizzaOrder){

        this.pizzaOrderRepository.saveAndFlush(pizzaOrder);
    }
}
