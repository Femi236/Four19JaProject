package com.four19ja.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

//@Embeddable
public class UserRoleID implements Serializable {
//    public static final long serialVersionUID = 12346;
    private Integer userID;
    private Integer roleID;

    public UserRoleID() {
    }

    public UserRoleID(Integer userID, Integer roleID) {
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
}
