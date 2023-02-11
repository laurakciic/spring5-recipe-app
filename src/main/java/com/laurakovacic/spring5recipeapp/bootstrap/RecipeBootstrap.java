package com.laurakovacic.spring5recipeapp.bootstrap;

import com.laurakovacic.spring5recipeapp.model.*;
import com.laurakovacic.spring5recipeapp.repositories.CategoryRepository;
import com.laurakovacic.spring5recipeapp.repositories.RecipeRepository;
import com.laurakovacic.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs, check if they're in the DB
        UnitOfMeasure eachUom = getUnitOfMeasure("Each");
        UnitOfMeasure tableSpoonUom = getUnitOfMeasure("Tablespoon");
        UnitOfMeasure teaSpoonUom = getUnitOfMeasure("Teaspoon");
        UnitOfMeasure dashUom = getUnitOfMeasure("Dash");
        UnitOfMeasure pintUom = getUnitOfMeasure("Pint");
        UnitOfMeasure cupUom = getUnitOfMeasure("Cup");

        // get categories
        Category americanCategory = getCategory("American");
        Category mexicanCategory = getCategory("Mexican");

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("The Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("""
                1. Cut the avocados:
                Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.
                                
                2. Mash the avocado flesh:
                Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)
                                
                3. Add the remaining ingredients to taste:
                Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.
                                
                Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.
                                
                Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.
                                
                4. Serve immediately:
                If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)
                                
                Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.
                                
                Refrigerate leftover guacamole up to 3 days.
                """);
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("""
                Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.
                
                Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.
                """);

        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh lime or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black peppa", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.getCategories().add(americanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(10);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("""
                1. Prepare the grill:
                Prepare either a gas or charcoal grill for medium-high, direct heat.
                                
                2. Make the marinade and coat the chicken:
                In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.
                                
                Set aside to marinate while the grill heats and you prepare the rest of the toppings.
                                
                3. Grill the chicken:
                Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.
                                
                4. Thin the sour cream with milk:
                Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.
                                
                5. Assemble the tacos:
                Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.
                                
                6. Warm the tortillas:
                Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.
                                
                Wrap warmed tortillas in a tea towel to keep them warm until serving.
                """);

        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("""
                Look for ancho chile powder with the Mexican ingredients at your grocery store, or buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)
                """);
        tacosNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, chopped", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Finely grated orange zest", new BigDecimal(1), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Small corn tortillas", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("Medium ripe avocados, slice", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.addIngredient(new Ingredient("Red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredient("Lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(mexicanCategory);
        tacosRecipe.getCategories().add(americanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }

    private UnitOfMeasure getUnitOfMeasure(String description) {
        return unitOfMeasureRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Unit of measure " + description + " not found"));
    }

    private Category getCategory(String description) {
        return categoryRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Category " + description + " not found."));
    }
}
