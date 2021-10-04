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

    /**
     * Create a new userRole.
     *
     * @param user_id the id of the user
     * @param role_id the id of the role
     * @return the status of the request
     */
    public String addNewUserRole(Integer user_id, Integer role_id) {
        UserRole userRole = new UserRole(user_id, role_id);
        userRoleRepository.save(userRole);
        return "Saved";
    }

    /**
     * Update a userRole.
     *
     * @param id the id of the userRole
     * @return the status of the request
     */
    public String deleteUserRole(UserRoleID id) {
        UserRole userRole = userRoleRepository.findById(id).orElse(null);
        if(userRole == null) {
            return "Does not exist";
        }
        userRoleRepository.deleteById(id);
        return "Deleted";
    }
}
