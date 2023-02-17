package com.laurakovacic.spring5recipeapp.repositories;

import com.laurakovacic.spring5recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest                        // brings up embedded DB and will configure Spring Data JPA
class UnitOfMeasureRepositoryIT {
    @Autowired                      // DI for this integration test (instance of UOMRepo will be injected into this)
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", uomOptional.get().getDescription());
    }
}