package com.four19ja.controllers;

import com.four19ja.TestConfig;
import com.four19ja.security.JwtConfig;
import com.four19ja.services.RoleService;
import com.four19ja.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = RoleController.class)
@Import(TestConfig.class)
public class RoleControllerTest {

    private final transient String contentType = "application/json";
    private final transient String saved = "saved";
    private final transient String updated = "Updated";
    private final transient String deleted = "Deleted";

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private transient RoleService roleService;

    @MockBean
    private transient UserService userDetailsService;

    @Autowired
    private transient JwtConfig jwtConfig;

    @BeforeEach
    public void init() {

    }

    @Test
    @WithMockUser
    public void whenValidInput_addsRole() throws Exception {
        when(roleService.addNewRole(any(String.class))).thenReturn(saved);

        MvcResult mvcResult = mockMvc.perform(post("/role/add").contentType(contentType)
        .param("name", "name")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(saved, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_updatesRole() throws Exception {
        when(roleService.updateRole(any(Integer.class),any(String.class))).thenReturn(updated);

        MvcResult mvcResult = mockMvc.perform(post("/role/update").contentType(contentType)
                .param("id", "1")
                .param("name", "name")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(updated, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_deletesRole() throws Exception {
        when(roleService.deleteRole(any(Integer.class))).thenReturn(deleted);

        MvcResult mvcResult = mockMvc.perform(post("/role/delete").contentType(contentType)
                .param("id", "1")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(deleted, response);
    }


}
