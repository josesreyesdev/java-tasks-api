package com.jsrdev.TasksAPI.domain.service;

import com.jsrdev.TasksAPI.domain.repository.UserRepository;
import com.jsrdev.TasksAPI.domain.models.user.AddUserRequest;
import com.jsrdev.TasksAPI.domain.models.user.UpdateUser;
import com.jsrdev.TasksAPI.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public User save(AddUserRequest addUser) {
        User user = new User(addUser, encoder);
        return userRepository.save(user);
    }

    public Page<User> findByActiveTrue(Pageable pageable) {
        return userRepository.findByActiveTrue(pageable);
    }

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public void updateUser(User user, UpdateUser updateUser) {
        user.updateData(updateUser, encoder);
    }

    public void deactivateUser(User user) {
        user.deactivateUser();
    }
}
