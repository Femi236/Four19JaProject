package com.four19ja.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ingredient_id")
    private Integer ingredientID;

    @Column(name = "name")
    private String name;

    public Ingredient() {
        super();
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Integer getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Integer ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return ingredientID.equals(that.ingredientID)
                && name.equals(that.name);
    }
}
