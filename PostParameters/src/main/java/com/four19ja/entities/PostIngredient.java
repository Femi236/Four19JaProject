package com.four19ja.entities;

import javax.persistence.*;

@Entity
@IdClass(PostIngredientID.class)
@Table(name = "postingredient")
public class PostIngredient {
    @Id
    @Column(name = "post")
    private Integer post;

    @Id
    @Column(name = "ingredient")
    private Integer ingredient;

    @Column(name = "amount")
    private String amount;

    //private String unit;

    public PostIngredient() {
    }

    public PostIngredient(Integer post, Integer ingredient, String amount) {
        this.post = post;
        this.ingredient = ingredient;
        this.amount = amount;
        //this.unit = unit;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getIngredient() {
        return ingredient;
    }

    public void setIngredient(Integer ingredient) {
        this.ingredient = ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostIngredient that = (PostIngredient) o;
        return post.equals(that.post)
                && ingredient.equals(that.ingredient)
                && amount.equals(that.amount);
    }
}
