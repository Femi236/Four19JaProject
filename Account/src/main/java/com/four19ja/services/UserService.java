package com.four19ja.services;

import com.four19ja.dto.PublicUser;
import com.four19ja.entities.Role;
import com.four19ja.entities.User;
import com.four19ja.entities.UserRole;
import com.four19ja.repositories.RoleRepository;
import com.four19ja.repositories.UserRepository;
import com.four19ja.repositories.UserRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Load a user by its username.
     *
     * @param username the username of the user
     * @return a userDetails user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoleRepository.findAllByUserID(user.getId()).forEach(userRole -> {
            authorities.add(new SimpleGrantedAuthority(roleRepository.findById(userRole.getRoleID()).orElse(null).getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Create a new user.
     *
     * @param username the username of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @return the status of the request
     */
    public String addNewUser(String username, String firstName, String lastName, String email, String password) {
        User user = new User(username, firstName, lastName, email, password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Saved";
    }

    /**
     * Register a new user.
     *
     * @param username the username of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @return the status of the request
     */
    public String register(String username, String firstName, String lastName, String email, String password) {
        User user = new User(username, firstName, lastName, email, password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Role role = roleRepository.findByName("subscriber");
        UserRole userRole = new UserRole(user.getId(), role.getId());
        userRoleRepository.save(userRole);
        return "Saved";
    }

    /**
     * Update the details of a user.
     *
     * @param id the id of the user to update
     * @param username the new username of the user
     * @param firstName the new first name of the user
     * @param lastName the new last name of the user
     * @param email the new email of the user
     * @param password the new password of the user
     * @return the status of the request
     */
    public String updateUser(Integer id, String username, String firstName, String lastName, String email, String password) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return "Not Saved";
        }
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "Saved";
    }

    /**
     * Delete a user.
     *
     * @param id the id of the user to delete
     * @return the status of the request
     */
    public String deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return "Does not exist";
        }
        userRepository.deleteById(id);
        return "Deleted";
    }

    /**
     * Get a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return the user
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Get the UserRoles associated with a user.
     *
     * @param id the id of the user
     * @return the user roles associated with the user
     */
    public Collection<UserRole> getUserRoles(Integer id) {
        return userRoleRepository.findAllByUserID(id);
    }

    /**
     * Get the details of the currently signed in user.
     * @param authentication
     * @return the details of the user
     */
    public PublicUser getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        PublicUser publicUser = new PublicUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
        return publicUser;

    }

}
