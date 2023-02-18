package com.laurakovacic.spring5recipeapp.services;

import com.laurakovacic.spring5recipeapp.commands.RecipeCommand;
import com.laurakovacic.spring5recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
