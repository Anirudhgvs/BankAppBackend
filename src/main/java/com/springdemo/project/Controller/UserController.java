package com.springdemo.project.Controller;

import com.springdemo.project.Entity.User;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

    @GetMapping("/me")
    public ResponseEntity<User> fetchUser(@RequestBody User request) {
        Optional<User> optionalUser = userService.getByUserName(request.getUsername());
        return optionalUser.map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
