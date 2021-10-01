package com.four19ja.controllers;

import com.four19ja.entities.Comment;
import com.four19ja.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/userActions/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addNewComment(@RequestParam Integer commenter, @RequestParam String comment,
                                              @RequestParam Integer post) {
        return commentService.addNewComment(commenter, comment, post);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateComment(@RequestParam Integer id, @RequestParam String comment) {
        return commentService.updateComment(id, comment);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody String deleteComment(@RequestParam Integer id) {
        return commentService.deleteComment(id);
    }
}
