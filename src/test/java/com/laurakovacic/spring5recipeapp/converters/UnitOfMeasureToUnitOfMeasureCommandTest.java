package com.laurakovacic.spring5recipeapp.converters;

import com.laurakovacic.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.laurakovacic.spring5recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static Long ID_VALUE = 1L;
    public static String DESCRIPTION = "desc";
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullObject() {
        assertNull( converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        // then
        assertEquals(ID_VALUE, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }
}