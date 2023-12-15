package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.model.Views;
import com.example.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @JsonView(Views.MyResponseViews.class)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId,
                                        @RequestParam(name = "accessToken",required = false) String accessToken) {
        User user = userService.getUserById(userId, accessToken);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
