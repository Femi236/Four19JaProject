package com.four19ja.controllers;

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

    /**
     * Create a new role.
     *
     * @param name the name of the role
     * @return the status of the request
     */
    @PostMapping(path = "/add")
    public @ResponseBody String addNewRole(@RequestParam String name) {
        return roleService.addNewRole(name);
    }

    /**
     * Update a role.
     * @param id the id of the role
     * @param name the new name of the role
     * @return the status of the request
     */
    @PostMapping(path = "/update")
    public @ResponseBody String updateRole(@RequestParam Integer id, @RequestParam String name) {
        return roleService.updateRole(id, name);
    }

    /**
     * Delete a role.
     * @param id the id of the role to delete.
     * @return the status of the request
     */
    @PostMapping(path = "/delete")
    public @ResponseBody String deleteRole(@RequestParam Integer id) {
        return roleService.deleteRole(id);
    }
}