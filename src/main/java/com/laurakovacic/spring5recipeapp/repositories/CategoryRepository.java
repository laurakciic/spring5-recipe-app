package com.laurakovacic.spring5recipeapp.repositories;

import com.laurakovacic.spring5recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
