package com.practice.user_profile_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.user_profile_management_system.Repos.UserRepository;
import com.practice.user_profile_management_system.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
