package com.four19ja.repositories;

import com.four19ja.entities.UserRole;
import com.four19ja.entities.UserRoleID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleID> {
    List<UserRole> findAllByUserID(Integer userId);
}