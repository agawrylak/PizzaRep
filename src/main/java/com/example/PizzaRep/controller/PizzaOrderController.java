package com.example.PizzaRep.controller;

import com.example.PizzaRep.business.model.Ingredient;
import com.example.PizzaRep.business.model.Pizza;
import com.example.PizzaRep.business.model.PizzaOrder;
import com.example.PizzaRep.services.PizzaOrderService;
import com.example.PizzaRep.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PizzaOrderController {

    @Autowired
    PizzaOrderService pizzaOrderService;
    @Autowired
    PizzaService pizzaService;

    public PizzaOrderController() {
        super();
    }

    @ModelAttribute("chosenPizzas")
    public List<Pizza> populateChosenPizza() {
        return this.pizzaService.findAllChosen();
    }

    @ModelAttribute("allPizzas")
    public List<Pizza> populatePizza() {
        return this.pizzaService.findAll();
    }

    @ModelAttribute("pizzaPrices")
    public double calculatePizzaPrices() {
        double result = 0;
        for(Pizza pizza : pizzaService.findAllChosen()){
            result+=10;
            for (Ingredient ingredient: pizza.getIngredients()){
                result+=3.50;
            }
        }
        return result;
    }

    @GetMapping("/ordermanager")
    public String pizzaForm(Model model) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        model.addAttribute("pizzaorder", pizzaOrder);

        return "ordermanager";
    }

    @RequestMapping({"/", "/ordermanager"})
    public String showOrders() {
        return "ordermanager";
    }

    @RequestMapping(value = "/ordermanager", params = {"create_order"}, method = RequestMethod.POST)
    public String savePizza(final PizzaOrder pizzaOrder, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "pizzamanager";
        }

        pizzaOrder.setPizzas(pizzaService.findAllChosen());
        this.pizzaOrderService.add(pizzaOrder);
        model.clear();
        return "redirect:/pizzamanager";
    }

    @RequestMapping(value = "/ordermanager/order")
    public String clickOrder(@RequestBody String integer,final ModelMap model) {
        integer=integer.replaceAll("=","");
        Pizza p = this.pizzaService.findbyId(Integer.valueOf(integer));
        p.setChosen(!p.isChosen());
        this.pizzaService.add(p);
        return "redirect:/pizzamanager";
    }

}
