package com.four19ja.entities;

import javax.persistence.*;

@Entity
@IdClass(UserRoleID.class)
@Table(name = "user_role")
public class UserRole {
    @Id
    @Column(name = "user_id")
    private Integer userID;

    @Id
    @Column(name = "role_id")
    private Integer roleID;

    public UserRole() {
    }

    public UserRole(Integer userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return roleID.equals(userRole.roleID) && roleID.equals(userRole.roleID);
    }
}