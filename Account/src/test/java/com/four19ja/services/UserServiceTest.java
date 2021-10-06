package com.four19ja.services;

import com.four19ja.entities.Role;
import com.four19ja.entities.User;
import com.four19ja.entities.UserRole;
import com.four19ja.repositories.RoleRepository;
import com.four19ja.repositories.UserRepository;
import com.four19ja.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private transient User user1;
    private transient User user2;
    private transient Role role1;
    private transient Role role2;
    private transient UserRole userRole1;
    private transient UserRole userRole2;
    private transient ArrayList<UserRole> userRoles;
    private transient UserDetails userDetails;

    @Mock
    private transient UserRepository userRepository;

    @Mock
    private transient RoleRepository roleRepository;

    @Mock
    private transient UserRoleRepository userRoleRepository;

    @Mock
    private transient PasswordEncoder passwordEncoder;

    private transient UserService userService;

    @BeforeEach
    public void init() {
        user1 = new User("username", "firstName", "lastName", "email", "password");
        user1.setId(1);
        user2 = new User("username", "firstName", "lastName", "email", "password");
        user2.setId(2);
        role1 = new Role("name");
        role1.setId(1);
        userDetails = new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(), new ArrayList<>());
        userService = new UserService(userRepository, userRoleRepository, roleRepository, passwordEncoder);
    }

    @Test
    public void loadValidUsername() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(user1);
        when(userRoleRepository.findAllByUserID(any(Integer.class))).thenReturn(new ArrayList<>());
        UserDetails response = userService.loadUserByUsername(user1.getUsername());
        assertEquals(userDetails, response);
    }

    @Test
    public void loadNonExistingUsername() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(null);
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(user1.getUsername());
        });
        assertEquals("User not found in the database", exception.getMessage());
    }

    @Test
    public void saveValidUser() {
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        String response = userService.addNewUser(user1.getUsername(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword());
        assertEquals("Saved", response);
    }

    @Test
    public void registerValidUser() {
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        when(roleRepository.findByName(any(String.class))).thenReturn(role1);
        when(userRoleRepository.save(any(UserRole.class))).then(returnsFirstArg());
        String response = userService.register(user1.getUsername(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword());
        assertEquals("Saved", response);
    }

    @Test
    public void updateValidUser() {
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(user1));
        when(userRepository.save(any(User.class))).then(returnsFirstArg());
        String response = userService.updateUser(user1.getId(), user1.getUsername(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword());
        assertEquals("Saved", response);
    }

    @Test
    public void updateNonExistingUser() {
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
        String response = userService.updateUser(user1.getId(), user1.getUsername(), user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword());
        assertEquals("Not Saved", response);
    }


    @Test
    public void deleteValidUser() {
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(user1));
        doNothing().when(userRepository).deleteById(any(Integer.class));
        String response = userService.deleteUser(user1.getId());
        assertEquals("Deleted", response);
    }

    @Test
    public void deleteNonExistingUser() {
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));
        String response = userService.deleteUser(user2.getId());
        assertEquals("Does not exist", response);
    }

    @Test
    public void getValidUserByUsername() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(user1);
        User response = userService.getUserByUsername(user1.getUsername());
        assertEquals(user1, response);
    }

    @Test
    public void getValidUserRoles() {
        role2 = new Role("name");
        role2.setId(2);
        userRole1 = new UserRole(user1.getId(), role1.getId());
        userRole2 = new UserRole(user1.getId(), role2.getId());
        userRoles = new ArrayList<>();
        userRoles.add(userRole1);
        userRoles.add(userRole2);
        when(userRoleRepository.findAllByUserID(any(Integer.class))).thenReturn(userRoles);
        Collection<UserRole> response = userService.getUserRoles(user1.getId());
        assertEquals(userRoles, response);
    }
}
