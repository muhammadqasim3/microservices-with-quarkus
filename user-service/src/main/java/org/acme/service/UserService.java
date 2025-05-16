package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.entity.User;
import org.acme.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(Long id) {
        return User.findById(id);
    }

    public List<User> getAllUsers() {
        return User.listAll();
    }

    @Transactional
    public User createUser(User user) {
        user.persist();
        return user;
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = User.findById(id);
        if (existingUser != null) {
            existingUser.name = user.name;
            existingUser.email = user.email;
            existingUser.persist();
        }
        return existingUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        User existingUser = User.findById(id);
        if (existingUser != null) {
            existingUser.delete();
        }
    }

}
