package com.example.bookUp.facade;

import com.example.bookUp.model.User;
import com.example.bookUp.model.Role;
import com.example.bookUp.factory.UserFactory;
import com.example.bookUp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public UserFacadeImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    @Transactional
    public User createUser(String email, String password, String firstName, String lastName, Role role) {
        if (existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = userFactory.createUser(email, password, firstName, lastName, role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDetails.getEmail());
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setRole(userDetails.getRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
} 