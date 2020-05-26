package com.example.PizzaRep.controller;

import com.example.PizzaRep.model.Ingredient;
import com.example.PizzaRep.model.Pizza;
import com.example.PizzaRep.model.POJO.PizzaPOJO;
import com.example.PizzaRep.repository.IngredientRepository;
import com.example.PizzaRep.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PizzaController{

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("pizza/testcreate")
    public String testCreate(){

        Ingredient cheese = new Ingredient("Cheese");
        Ingredient tomatoSauce = new Ingredient("Tomato Sauce");
        Ingredient pineapple = new Ingredient("Pineapple");
        Ingredient bacon = new Ingredient("Bacon");

        pizzaRepository.save(new Pizza("Bianca"));
        pizzaRepository.save(new Pizza("Margarita",tomatoSauce, cheese));
        pizzaRepository.save(new Pizza("Hawaiian", tomatoSauce, cheese, pineapple, bacon));

        return "Pizza created";
    }

    @PostMapping("pizza/create")
    public String create(@RequestBody PizzaPOJO pizza){
        Pizza newPizza = new Pizza(pizza.getName());
        for (String ingredient:pizza.getIngredients()
             ) {
            newPizza.getIngredients().add(new Ingredient(ingredient));
        }
        pizzaRepository.save(newPizza);

        return "Pizza is created";
    }

    @RequestMapping("pizza/update/{id}/{ingredient}")
    public String addIngredientToPizza(@PathVariable long id, @PathVariable String ingredient){
        Ingredient tempIngredient;
        System.out.println(ingredientRepository.findByName(ingredient));
        if(ingredientRepository.findByName(ingredient) == null){
            tempIngredient = new Ingredient(ingredient);

        }else{
            tempIngredient = ingredientRepository.findByName(ingredient);
        }
        pizzaRepository.findById(id).get().getIngredients().add(tempIngredient);
        pizzaRepository.flush();
        return "Pizza succesfully updated.";
    }

    @GetMapping("pizza/findall")
    public List<PizzaPOJO> findAll(){
        List<Pizza> pizzas = pizzaRepository.findAll();
        List<PizzaPOJO> pizzaPOJOS = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            PizzaPOJO tempPizza = new PizzaPOJO(pizza.getName());

            String[] tempPizzaIngredients = new String[pizza.getIngredients().size()];

            int i=0;
            for(Ingredient ingredient : pizza.getIngredients()){
                tempPizzaIngredients[i] = ingredient.getName();
                i++;
            }


            tempPizza.setIngredients(tempPizzaIngredients);
            pizzaPOJOS.add(tempPizza);
        }
        return pizzaPOJOS;
    }
    @RequestMapping("pizza/search/{id}")
    public String fetchDataByPizzaID(@PathVariable long id){
        String pizza = "";
        pizza = pizzaRepository.findById(id).toString();
        return pizza;
    }

    @RequestMapping("pizza/searchbyname/{name}")
    public List<PizzaPOJO> fetchDataByPizzaName(@PathVariable String name){
        List<Pizza> pizzas = pizzaRepository.findByName(name);
        List<PizzaPOJO> pizzaPOJOS = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            PizzaPOJO tempPizza = new PizzaPOJO(pizza.getName());

            String[] tempPizzaIngredients = new String[pizza.getIngredients().size()];

            int i=0;
            for(Ingredient ingredient : pizza.getIngredients()){
                tempPizzaIngredients[i] = ingredient.getName();
                i++;
            }


            tempPizza.setIngredients(tempPizzaIngredients);
            pizzaPOJOS.add(tempPizza);
        }
        return pizzaPOJOS;
    }

    @RequestMapping("ingredient/searchbyname/{name}")
    public List<PizzaPOJO> fetchDataByIngredientName(@PathVariable String name){
        List<Pizza> pizzas = pizzaRepository.findByIngredientName(name);
        List<PizzaPOJO> pizzaPOJOS = new ArrayList<>();

        for (Pizza pizza : pizzas) {
            PizzaPOJO tempPizza = new PizzaPOJO(pizza.getName());

            String[] tempPizzaIngredients = new String[pizza.getIngredients().size()];

            int i=0;
            for(Ingredient ingredient : pizza.getIngredients()){
                tempPizzaIngredients[i] = ingredient.getName();
                i++;
            }


            tempPizza.setIngredients(tempPizzaIngredients);
            pizzaPOJOS.add(tempPizza);
        }
        return pizzaPOJOS;
    }


    @RequestMapping("pizza/delete/id/{id}")
    public String deleteByID(@PathVariable String id){
        pizzaRepository.deleteById(Long.parseLong(id));
        return "Pizza has been deleted";
    }

    @RequestMapping("pizza/delete/name/{name}")
    public String deleteByPizzaName(@PathVariable String name){
        List<Pizza> list = pizzaRepository.findByName(name);
        for (Pizza pizza: list
             ) {
            pizzaRepository.deleteById(pizza.getId());

        }
        return "Pizzas with selected name have been deleted";
    }
    @RequestMapping("ingredient/delete/name/{name}")
    public String deleteByIngredientName(@PathVariable String name){
        List<Pizza> list = pizzaRepository.findByName(name);
        for (Pizza pizza: list
        ) {
            pizzaRepository.deleteById(pizza.getId());

        }
        return "Pizzas with selected name have been deleted";
    }

}