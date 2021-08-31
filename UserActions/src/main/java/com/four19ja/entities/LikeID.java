package com.four19ja.entities;

import java.io.Serializable;

public class LikeID implements Serializable {
    private Integer user;

    private Integer post;

    public LikeID() {
        super();
    }

    public LikeID(Integer user, Integer post) {
        this.user = user;
        this.post = post;
    }
}
