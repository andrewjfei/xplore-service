package dev.andrewjfei.xploreservice.controller;

import dev.andrewjfei.xploreservice.service.UserService;
import dev.andrewjfei.xploreservice.transaction.request.LoginRequest;
import dev.andrewjfei.xploreservice.transaction.request.NewUserRequest;
import dev.andrewjfei.xploreservice.transaction.response.UserListResponse;
import dev.andrewjfei.xploreservice.transaction.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = userService.authenticateUser(loginRequest);
        return new ResponseEntity<>(userResponse, OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody NewUserRequest newUserRequest) {
        UserResponse userResponse = userService.createUser(newUserRequest);
        return new ResponseEntity<>(userResponse, CREATED);
    }

    @GetMapping
    public ResponseEntity<UserListResponse> fetchAllUsers() {
        UserListResponse userListResponse = userService.retrieveAllUsers();
        return new ResponseEntity<>(userListResponse, OK);
    }
}
