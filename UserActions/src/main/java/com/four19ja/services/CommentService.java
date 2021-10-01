package com.four19ja.services;

import com.four19ja.entities.Comment;
import com.four19ja.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public String addNewComment(Integer commenter, String comment, Integer post) {
        Comment c = new Comment();
        c.setCommenter(commenter);
        c.setComment(comment);
        c.setPost(post);
        commentRepository.save(c);
        return "Saved";
    }

    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public String updateComment(Integer id, String comment) {
        Comment c = commentRepository.findById(id).orElse(null);
        if(c == null) {
            return "Not Saved";
        }
        c.setComment(comment);
        commentRepository.save(c);
        return "Saved";
    }

    public String deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null) {
            return "Does not exist";
        }
        commentRepository.deleteById(id);
        return "Deleted";
    }
}
