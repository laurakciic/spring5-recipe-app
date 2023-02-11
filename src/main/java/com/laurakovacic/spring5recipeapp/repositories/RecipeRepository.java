package com.laurakovacic.spring5recipeapp.repositories;

import com.laurakovacic.spring5recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
