package com.four19ja.repositories;

import com.four19ja.entities.UserRole;
import com.four19ja.entities.UserRoleID;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleID> {

}