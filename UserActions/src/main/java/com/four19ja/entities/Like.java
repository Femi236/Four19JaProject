package com.four19ja.entities;

import javax.persistence.*;

@Entity
@IdClass(LikeID.class)
@Table(name = "liked_post")
public class Like {

    @Id
    @Column(name = "user")
    private Integer user;

    @Id
    @Column(name = "post")
    private Integer post;

    public Like() {
        super();
    }

    public Like(Integer user, Integer post) {
        this.user = user;
        this.post = post;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Like like = (Like) o;
        return user.equals(like.user)
                && post.equals(like.post);
    }
}
