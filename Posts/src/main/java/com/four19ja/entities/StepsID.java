package com.four19ja.entities;

import java.io.Serializable;

public class StepsID implements Serializable {
    private Integer post;
    private Integer stepNumber;

    public StepsID(){

    }

    public StepsID(Integer post, Integer stepNumber) {
        this.post = post;
        this.stepNumber = stepNumber;
    }
}
