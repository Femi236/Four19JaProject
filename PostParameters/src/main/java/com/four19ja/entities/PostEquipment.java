package com.four19ja.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(PostEquipmentID.class)
@Table(name = "postequipment")
public class PostEquipment {
    @Id
    @Column(name = "post")
    private Integer post;

    @Id
    @Column(name = "equipment")
    private Integer equipment;

    public PostEquipment() {
        super();
    }

    public PostEquipment(Integer post, Integer equipment) {
        this.post = post;
        this.equipment = equipment;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostEquipment that = (PostEquipment) o;
        return post.equals(that.post)
                && equipment.equals(that.equipment);
    }
}
