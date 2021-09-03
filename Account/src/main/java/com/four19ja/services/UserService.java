package com.four19ja.services;

import com.four19ja.entities.User;
import com.four19ja.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addNewUser(String username, String firstName, String lastName, String email, String password) {
        User user = new User(username, firstName, lastName, email, password);
        userRepository.save(user);
        return "Saved";
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

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

    public String deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return "Does not exist";
        }
        userRepository.deleteById(id);
        return "Deleted";
    }
}
