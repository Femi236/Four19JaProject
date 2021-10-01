package com.four19ja.services;

import com.four19ja.entities.Like;
import com.four19ja.entities.LikeID;
import com.four19ja.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public String addNewLike(Integer user, Integer post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
        return "Saved";
    }

    public Iterable<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public String deleteLike(Integer user, Integer post) {
        LikeID likeID = new LikeID(user, post);
        Like like = likeRepository.findById(likeID).orElse(null);
        if(like == null) {
            return "Does not exist";
        }
        likeRepository.deleteById(likeID);
        return "Deleted";
    }
}
