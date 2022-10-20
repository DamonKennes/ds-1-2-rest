package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @GetMapping("/restrpc/cheapest")
    Meal getCheapestMeal() {
        return mealsRepository.findCheapestMeal();
    }

    @GetMapping("/restrpc/largest")
    Meal getLargestMeal() {
        return mealsRepository.findLargestMeal();
    }

    @DeleteMapping("/restrpc/meals/{id}")
    void deleteMeal(@PathVariable String id){
        // wat returnen?
        mealsRepository.deleteMeal(id);
    }

    @PostMapping("/restrpc/meals")
    String addMeal(@RequestBody Meal meal){
        meal.setId(Integer.toHexString(meal.hashCode()));
        mealsRepository.addMeal(meal);
        return meal.getId();
    }

    @PutMapping("/restrpc/meals/{id}")
    void updateMeal(@PathVariable String id, @RequestBody Meal meal){
        mealsRepository.updateMeal(id, meal);
    }

}
