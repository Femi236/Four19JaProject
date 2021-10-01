package com.four19ja.controllers;

import com.four19ja.entities.Follow;
import com.four19ja.services.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/userActions/follow")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewFollow(@RequestParam Integer follower, @RequestParam Integer followed) {
        return followService.addNewFollow(follower, followed);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Follow> getAllFollows() {
        return followService.getAllFollows();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteFollow(@RequestParam Integer follower, @RequestParam Integer followed) {
        return followService.deleteFollow(follower, followed);
    }
}
