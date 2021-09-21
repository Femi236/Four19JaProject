package com.four19ja.services;

import com.four19ja.entities.Follow;
import com.four19ja.entities.FollowID;
import com.four19ja.repositories.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public String addNewFollow(Integer follower, Integer followed) {
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowed(followed);
        followRepository.save(follow);
        return "Saved";
    }

    public Iterable<Follow> getAllFollows() {
        return followRepository.findAll();
    }

    public String deleteFollow(Integer follower, Integer followed) {
        FollowID followID = new FollowID(follower, followed);
        Follow follow = followRepository.findById(followID).orElse(null);
        if(follow == null) {
            return "Does not exist";
        }
        followRepository.deleteById(followID);
        return "Deleted";
    }
}
