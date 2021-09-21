package com.four19ja.controllers;

import com.four19ja.entities.Like;
import com.four19ja.services.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/userActions/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewLike(@RequestParam Integer user, @RequestParam Integer post) {
        return likeService.addNewLike(user, post);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteLike(@RequestParam Integer user, @RequestParam Integer post) {
        return likeService.deleteLike(user, post);
    }
}
