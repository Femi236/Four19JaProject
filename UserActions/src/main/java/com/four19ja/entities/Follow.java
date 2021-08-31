package com.four19ja.entities;

import javax.persistence.*;

@Entity
@IdClass(FollowID.class)
@Table(name = "follow")
public class Follow {
    @Id
    @Column(name = "follower")
    private Integer follower;

    @Id
    @Column(name = "followed")
    private Integer followed;

    public Follow() {
    }

    public Follow(Integer follower, Integer followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getFollowed() {
        return followed;
    }

    public void setFollowed(Integer followed) {
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Follow follow = (Follow) o;
        return follower.equals(follow.follower)
                && followed.equals(follow.followed);
    }
}
