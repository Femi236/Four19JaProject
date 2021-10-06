package com.four19ja.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.four19ja.TestConfig;
import com.four19ja.entities.UserRoleID;
import com.four19ja.security.JwtConfig;
import com.four19ja.services.RoleService;
import com.four19ja.services.UserRoleService;
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

@WebMvcTest(controllers = UserRoleController.class)
@Import(TestConfig.class)
public class UserRoleControllerTest {

    private final transient String contentType = "application/json";
    private final transient String saved = "saved";
    private final transient String deleted = "Deleted";

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private transient UserRoleService userRoleService;

    @MockBean
    private transient UserService userDetailsService;

    @Autowired
    private transient ObjectMapper objectMapper;

    @Autowired
    private transient JwtConfig jwtConfig;

    private transient UserRoleID userRoleID;

    @BeforeEach
    public void init() {
        userRoleID = new UserRoleID(1,1);
    }

    @Test
    @WithMockUser
    public void whenValidInput_addsUserRole() throws Exception {
        when(userRoleService.addNewUserRole(any(Integer.class), any(Integer.class))).thenReturn(saved);

        MvcResult mvcResult = mockMvc.perform(post("/userRole/add").contentType(contentType)
                .param("user_id", "1").param("role_id", "1")).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(saved, response);
    }

    @Test
    @WithMockUser
    public void whenValidInput_deletesUserRole() throws Exception {
        when(userRoleService.deleteUserRole(any(UserRoleID.class))).thenReturn(deleted);

        MvcResult mvcResult = mockMvc.perform(post("/userRole/delete").contentType(contentType)
                .content(objectMapper.writeValueAsString(userRoleID))).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(deleted, response);
    }


}


