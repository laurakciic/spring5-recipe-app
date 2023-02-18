package com.laurakovacic.spring5recipeapp.converters;

import com.laurakovacic.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.laurakovacic.spring5recipeapp.model.UnitOfMeasure;
import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) return null;

        final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(source.getId());
        uomCommand.setDescription(source.getDescription());

        return uomCommand;
    }
}
