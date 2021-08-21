package com.four19ja.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@IdClass(PostCategoryID.class)
@Table(name = "postcategory")
public class PostCategory {
    @Id
    @Column(name = "post")
    private Integer post;

    @Id
    @Column(name = "category")
    private Integer category;

    public PostCategory() {

    }

    public PostCategory(Integer post, Integer category) {
        this.post = post;
        this.category = category;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostCategory that = (PostCategory) o;
        return post.equals(that.post)
                && category.equals(that.category);
    }
}