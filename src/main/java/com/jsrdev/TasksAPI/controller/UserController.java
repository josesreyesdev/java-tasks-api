package com.jsrdev.TasksAPI.controller;

import com.jsrdev.TasksAPI.domain.service.UserService;
import com.jsrdev.TasksAPI.domain.models.user.AddUserRequest;
import com.jsrdev.TasksAPI.domain.models.user.UpdateUser;
import com.jsrdev.TasksAPI.domain.models.user.User;
import com.jsrdev.TasksAPI.domain.models.user.UserResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponse> createUser(
            @RequestBody @Valid AddUserRequest addUser,
            UriComponentsBuilder uriBuilder
    ) {
        User user = userService.save(addUser);
        URI url = uriBuilder.path("/api/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(url).body(new UserResponse(user)); // status 201-created
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @PageableDefault(size = 30, sort = {"id"}) Pageable pageable
    ) {
        Page<User> userPage = userService.findByActiveTrue(pageable);
        List<UserResponse> users = userPage.stream()
                .map(UserResponse::new)
                .toList();
        return ResponseEntity.ok(users); //status 200-ok
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        var user = userService.getReferenceById(id);
        return ResponseEntity.ok(new UserResponse(user)); // status 200-ok
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UpdateUser updateUser) {
        User user = userService.getReferenceById(updateUser.id());
        userService.updateUser(user, updateUser);
        return ResponseEntity.ok(new UserResponse(user)); //status 200-ok
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        User user = userService.getReferenceById(id);
        userService.deactivateUser(user);
        return ResponseEntity.noContent().build(); //status 204-No Content
    }
}
