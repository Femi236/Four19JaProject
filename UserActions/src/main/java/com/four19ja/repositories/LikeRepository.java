package com.four19ja.repositories;

import com.four19ja.entities.Like;
import com.four19ja.entities.LikeID;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, LikeID> {
}
