package com.example.PizzaRep.business.repository;

import com.example.PizzaRep.business.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);

    List<Ingredient> findAll();


}
