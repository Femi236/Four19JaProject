package com.four19ja.services;

import com.four19ja.entities.Role;
import com.four19ja.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    private transient Role role1;
    private transient Role role2;

    @Mock
    private transient RoleRepository roleRepository;

    private transient RoleService roleService;

    @BeforeEach
    public void init() {
        role1 = new Role("name");
        role1.setId(1);
        role2 = new Role("name");
        role2.setId(2);
        roleService = new RoleService(roleRepository);
    }

    @Test
    public void saveValidRole() {
        when(roleRepository.save(any(Role.class))).then(returnsFirstArg());
        String response = roleService.addNewRole(role1.getName());
        assertEquals("Saved", response);

    }

    @Test
    public void updateValidRole() {
        when(roleRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(role2));
        when(roleRepository.save(any(Role.class))).then(returnsFirstArg());
        String response = roleService.updateRole(role1.getId(), role2.getName());
        assertEquals("Updated", response);
    }

    @Test
    public void updateNonExistingRole() {
        when(roleRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
        String response = roleService.updateRole(role1.getId(), role2.getName());
        assertEquals("Not Saved", response);
    }

    @Test
    public void deleteValidRole() {
        when(roleRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(role2));
        doNothing().when(roleRepository).deleteById(any(Integer.class));
        String response = roleService.deleteRole(role1.getId());
        assertEquals("Deleted", response);
    }

    @Test
    public void deleteNonExistingRole() {
        when(roleRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
        String response = roleService.deleteRole(role1.getId());
        assertEquals("Does not exist", response);
    }

}
