package com.example.PizzaRep.business.repository;

import com.example.PizzaRep.business.model.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<PizzaOrder, Long> {

    List<PizzaOrder> findAll();
}
