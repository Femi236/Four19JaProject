package com.four19ja.services;

import com.four19ja.entities.UserRole;
import com.four19ja.entities.UserRoleID;
import com.four19ja.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public String addNewUserRole(Integer user_id, Integer role_id) {
        UserRole userRole = new UserRole(user_id, role_id);
        userRoleRepository.save(userRole);
        return "Saved";
    }

    public Iterable<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    public String deleteUserRole(UserRoleID id) {
        UserRole userRole = userRoleRepository.findById(id).orElse(null);
        if(userRole == null) {
            return "Does not exist";
        }
        userRoleRepository.deleteById(id);
        return "Deleted";
    }
}
