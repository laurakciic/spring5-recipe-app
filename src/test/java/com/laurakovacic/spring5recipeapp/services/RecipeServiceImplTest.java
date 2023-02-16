package com.laurakovacic.spring5recipeapp.services;

import com.laurakovacic.spring5recipeapp.model.Recipe;
import com.laurakovacic.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeServiceImpl;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        // tells Mockito to scan this test class instance for any fields annotated with the @Mock annotation and
        // initialize those fields as mocks
        MockitoAnnotations.openMocks(this);

        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeServiceImpl.getRecipes();

        assertEquals(recipes.size(), 1);

        // verify that findAll was called only once
        verify(recipeRepository, times(1)).findAll();
    }
}