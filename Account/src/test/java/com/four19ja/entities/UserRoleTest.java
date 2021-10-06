package com.four19ja.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRoleTest {
    private transient Integer userID;
    private transient Integer roleID;
    private transient UserRole userRole;

    @BeforeEach
    public void initialize() {
        userID = 1;
        roleID = 1;
        userRole = new UserRole();
    }

    @Test
    public void constructorTest() {
        assertNotNull(userRole);
    }

    @Test
    public void getSetUserIdTest() {
        userRole.setUserID(userID);
        assertEquals(userID, userRole.getUserID());
    }

    @Test
    public void getSetRoleIdTest() {
        userRole.setRoleID(roleID);
        assertEquals(roleID, userRole.getRoleID());
    }
}
