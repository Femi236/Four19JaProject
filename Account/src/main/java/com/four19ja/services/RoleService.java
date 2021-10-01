package com.four19ja.services;

import com.four19ja.entities.Role;
import com.four19ja.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public String addNewRole(String name) {
        Role role = new Role(name);
        roleRepository.save(role);
        return "Saved";
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public String updateRole(Integer id, String name) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role == null) {
            return "Not Saved";
        }
        role.setName(name);
        roleRepository.save(role);
        return "Saved";
    }

    public String getRoleName(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
//        try{
            return role.getName();
//        } catch(Exception e) {
//            throw new ExistExc();
//        }

    }

    public String deleteRole(Integer id) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role == null) {
            return "Does not exist";
        }
        roleRepository.deleteById(id);
        return "Deleted";
    }
}
