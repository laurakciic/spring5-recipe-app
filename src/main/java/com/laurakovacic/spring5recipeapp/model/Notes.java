package com.laurakovacic.spring5recipeapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne   // not adding cascade operations bc if we delete notes object we don't want to delete recipe object
    private Recipe recipe;
    @Lob
    private String recipeNotes;
}
