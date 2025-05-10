package com.springdemo.project.Controller;

import com.springdemo.project.Entity.User;
import com.springdemo.project.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(PublicController.class);

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User request) {
        userService.createUser(request);
        return ResponseEntity.ok("User registered successfully");
    }


}
