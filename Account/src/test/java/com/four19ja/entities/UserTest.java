package com.four19ja.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {
    private transient Integer id;
    private transient String username;
    private transient String firstName;
    private transient String lastName;
    private transient String email;
    private transient String password;
    private transient User user;

    @BeforeEach
    public void initialize() {
        id = 1;
        username = "username";
        firstName = "firstName";
        lastName = "lastName";
        email = "email";
        password = "password";
        user = new User();
    }

    @Test
    public void constructorTest() {
        assertNotNull(user);
    }

    @Test
    public void getSetIdTest() {
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void getSetUsernameTest() {
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void getSetFirstNameTest() {
        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void getSetLastNameTest() {
        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void getSetEmailTest() {
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getSetPasswordTest() {
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
}
