package com.four19ja.entities;

import javax.persistence.*;

@Entity
@IdClass(StepsID.class)
@Table(name = "steps")
public class Steps {

    @Id
    @Column(name = "post")
    private Integer post;

    @Id
    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "instruction")
    private String instruction;

    public Steps() {

    }

    public Steps(Integer post, Integer stepNumber, String instruction) {
        this.post = post;
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Steps steps = (Steps) o;
        return post.equals(steps.post)
                && stepNumber.equals(steps.stepNumber)
                && instruction.equals(steps.instruction);
    }
}
