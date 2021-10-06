package com.four19ja.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleTest {
    private transient Integer id;
    private transient String name;
    private transient Role role;

    @BeforeEach
    public void initialize() {
        id = 1;
        name = "name";
        role = new Role();
    }

    @Test
    public void constructorTest() {
        assertNotNull(role);
    }

    @Test
    public void getSetIdTest() {
        role.setId(id);
        assertEquals(id, role.getId());
    }

    @Test
    public void getSetNameTest() {
        role.setName(name);
        assertEquals(name,role.getName());
    }

}
