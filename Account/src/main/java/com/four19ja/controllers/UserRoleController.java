package com.four19ja.controllers;

import com.four19ja.entities.UserRole;
import com.four19ja.entities.UserRoleID;
import com.four19ja.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewRole(@RequestParam Integer user_id, @RequestParam Integer role_id) {
        return userRoleService.addNewUserRole(user_id, role_id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserRole> getAllRole() {
        return userRoleService.getAllUserRoles();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteUserRole(@RequestBody UserRoleID id) {
        return userRoleService.deleteUserRole(id);
    }
}