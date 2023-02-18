package com.laurakovacic.spring5recipeapp.converters;

import com.laurakovacic.spring5recipeapp.commands.CategoryCommand;
import com.laurakovacic.spring5recipeapp.commands.IngredientCommand;
import com.laurakovacic.spring5recipeapp.commands.NotesCommand;
import com.laurakovacic.spring5recipeapp.commands.RecipeCommand;
import com.laurakovacic.spring5recipeapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

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
    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure())
        );
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        // given
        RecipeCommand command = new RecipeCommand();
        command.setSource(SOURCE);
        command.setServings(SERVINGS);
        command.setId(RECIPE_ID);
        command.setUrl(URL);
        command.setDescription(DESCRIPTION);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        command.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand1.setId(CATEG_ID_1);
        categoryCommand2.setId(CATEG_ID_2);
        command.getCategories().add(categoryCommand1);
        command.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand1.setId(INGR_ID_1);
        ingredientCommand2.setId(INGR_ID_2);
        command.getIngredients().add(ingredientCommand1);
        command.getIngredients().add(ingredientCommand2);

        // when
        Recipe recipe = converter.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }
}