package com.four19ja.controllers;

import com.four19ja.entities.Role;
import com.four19ja.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewRole(@RequestParam String name) {
        return roleService.addNewRole(name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateRole(@RequestParam Integer id, @RequestParam String name) {
        return roleService.updateRole(id, name);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteRole(@RequestParam Integer id) {
        return roleService.deleteRole(id);
    }
}