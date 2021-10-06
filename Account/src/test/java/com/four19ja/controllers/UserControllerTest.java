package com.four19ja.controllers;

import com.four19ja.TestConfig;
import com.four19ja.security.JwtConfig;
import com.four19ja.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {

    private final transient String contentType = "application/json";
    private final transient String saved = "saved";
    private final transient String updated = "Updated";
    private final transient String deleted = "Deleted";

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private transient UserService userDetailsService;

    @Autowired
    private transient JwtConfig jwtConfig;

    @BeforeEach
    public void init() {

    }

    @Test
    @WithMockUser
    public void whenValidInput_addsUser() throws Exception {
        when(userDetailsService.addNewUser(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(saved);

        MvcResult mvcResult = mockMvc.perform(post("/user/add").contentType(contentType)
                .param("username", "username")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("email", "email")
                .param("password", "password")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(saved, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_registersUser() throws Exception {
        when(userDetailsService.register(any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(saved);

        MvcResult mvcResult = mockMvc.perform(post("/user/register").contentType(contentType)
                .param("username", "username")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("email", "email")
                .param("password", "password")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(saved, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_updatesUser() throws Exception {
        when(userDetailsService.updateUser(any(Integer.class),any(String.class), any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(updated);

        MvcResult mvcResult = mockMvc.perform(post("/user/update").contentType(contentType)
                .param("id", "1")
                .param("username", "username")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("email", "email")
                .param("password", "password")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(updated, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_deletesUser() throws Exception {
        when(userDetailsService.deleteUser(any(Integer.class))).thenReturn(deleted);

        MvcResult mvcResult = mockMvc.perform(post("/user/delete").contentType(contentType)
                .param("id", "1")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(deleted, response);
    }
}

