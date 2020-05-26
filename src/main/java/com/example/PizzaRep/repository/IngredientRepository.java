package com.example.PizzaRep.repository;

import com.example.PizzaRep.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
   Ingredient findByName(String name);
    List<Ingredient> findAll();


}
