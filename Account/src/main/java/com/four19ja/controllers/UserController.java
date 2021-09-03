package com.four19ja.controllers;

import com.four19ja.entities.User;
import com.four19ja.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String firstName,
                                           @RequestParam String lastName, @RequestParam String email,
                                           @RequestParam String password) {
        return userService.addNewUser(username, firstName, lastName, email, password);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateUser(@RequestParam Integer id, @RequestParam String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
        return userService.updateUser(id, username, firstName, lastName, email, password);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }
}