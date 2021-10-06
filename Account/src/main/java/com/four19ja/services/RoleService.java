package com.four19ja.services;

import com.four19ja.entities.Role;
import com.four19ja.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Create a new role.
     *
     * @param name the name of the role
     * @return the status of the request
     */
    public String addNewRole(String name) {
        Role role = new Role(name);
        roleRepository.save(role);
        return "Saved";
    }

    /**
     * Update a role.
     * @param id the id of the role
     * @param name the new name of the role
     * @return the status of the request
     */
    public String updateRole(Integer id, String name) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role == null) {
            return "Not Saved";
        }
        role.setName(name);
        roleRepository.save(role);
        return "Updated";
    }

    /**
     * Get the name of a role by its id.
     * @param id the id of the role
     * @return the name of the role if it exists
     */
    public String getRoleName(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role == null){
            return role.getName();
        }
        throw new EntityNotFoundException();
    }

    /**
     * Delete a role.
     * @param id the id of the role to delete.
     * @return the status of the request
     */
    public String deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role == null) {
            return "Does not exist";
        }
        roleRepository.deleteById(id);
        return "Deleted";
    }
}
