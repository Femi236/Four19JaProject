package com.four19ja.repositories;

import com.four19ja.entities.Follow;
import com.four19ja.entities.FollowID;
import org.springframework.data.repository.CrudRepository;

public interface FollowRepository extends CrudRepository<Follow, FollowID> {
}
