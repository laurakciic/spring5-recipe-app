package com.laurakovacic.spring5recipeapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")   // property set in Recipe class
    private Set<Recipe> recipes;

}
