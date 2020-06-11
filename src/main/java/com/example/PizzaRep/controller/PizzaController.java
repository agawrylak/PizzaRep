package com.example.PizzaRep.controller;

import com.example.PizzaRep.business.model.Ingredient;
import com.example.PizzaRep.business.model.Pizza;
import com.example.PizzaRep.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PizzaController {


    @Autowired
    PizzaService pizzaService;
    @Autowired
    PizzaService pizzaOrderService;

    public PizzaController() {
        super();
    }

    @ModelAttribute("allPizzas")
    public List<Pizza> populatePizza() {
        return this.pizzaService.findAll();
    }

    @RequestMapping({"/", "/pizzamanager"})
    public String showPizzas() {
        return "pizzamanager";
    }

    @GetMapping("/pizzamanager")
    public String pizzaForm(Model model) {
        Pizza newPizza = new Pizza();
        model.addAttribute("pizza", newPizza);
        model.addAttribute("ingredients", newPizza.getIngredients());
        return "pizzamanager";
    }

    @RequestMapping(value = "/pizzamanager", params = {"save"}, method = RequestMethod.POST)
    public String savePizza(final Pizza pizza, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "pizzamanager";
        }

        this.pizzaService.add(pizza);
        model.clear();
        return "redirect:/pizzamanager";
    }

    @RequestMapping(value = "/pizzamanager", params = {"addIngredient"})
    public String addIngredient(final Pizza pizza, final BindingResult bindingResult) {
        pizza.getIngredients().add(new Ingredient());
        return "pizzamanager";
    }

    @RequestMapping(value = "/pizzamanager", params = {"removeIngredient"})
    public String removeIngredient(
            final Pizza pizza, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer ingredientId = Integer.valueOf(req.getParameter("removeIngredient"));
        pizza.getIngredients().remove(ingredientId.intValue());
        return "pizzamanager";
    }

    @RequestMapping(value = "/pizzamanager/order")
    public String clickOrder(@RequestBody String integer,final ModelMap model) {
        integer=integer.replaceAll("=","");
        Pizza p = this.pizzaService.findbyId(Integer.valueOf(integer));
        p.setChosen(!p.isChosen());
        this.pizzaService.add(p);
        model.clear();
        model.addAttribute("allPizzas",populatePizza());
        return "redirect:/pizzamanager";
    }


}