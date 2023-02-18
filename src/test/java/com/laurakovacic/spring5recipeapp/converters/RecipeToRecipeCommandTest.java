package com.laurakovacic.spring5recipeapp.converters;

import com.laurakovacic.spring5recipeapp.commands.RecipeCommand;
import com.laurakovacic.spring5recipeapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    public static final Long RECIPE_ID = 1L;
    public static final String DESCRIPTION = "desc";
    public static final Integer PREP_TIME = 5;
    public static final Integer COOK_TIME = 5;
    public static final Integer SERVINGS = 2;
    public static final String SOURCE = "Unknown";
    public static final String URL = "www.unknown.org";
    public static final String DIRECTIONS = "1.";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long NOTES_ID = 1L;
    public static final Long INGR_ID_1 = 1L;
    public static final Long INGR_ID_2 = 2L;
    public static final Long CATEG_ID_1 = 1L;
    public static final Long CATEG_ID_2 = 2L;
    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        // given
        Recipe recipe = new Recipe();
        recipe.setSource(SOURCE);
        recipe.setServings(SERVINGS);
        recipe.setId(RECIPE_ID);
        recipe.setUrl(URL);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1 = new Category();
        Category category2 = new Category();
        category1.setId(CATEG_ID_1);
        category2.setId(CATEG_ID_2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(INGR_ID_1);
        ingredient2.setId(INGR_ID_2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        // when
        RecipeCommand command = converter.convert(recipe);

        // then
        assertNotNull(command);
        assertEquals(SOURCE, command.getSource());
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(URL, command.getUrl());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getIngredients().size());
        assertEquals(2, command.getCategories().size());
    }
}