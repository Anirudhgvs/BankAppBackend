package com.springdemo.project.Controller;

import com.springdemo.project.DTO.LoanApplicationDTO;
import com.springdemo.project.Entity.Loan;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Service.LoanService;
import com.springdemo.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

//    @GetMapping("/me")
//    public ResponseEntity<User> fetchUser(@RequestBody User request) {
//        Optional<User> optionalUser = userService.getByUserName(request.getUsername());
//        return optionalUser.map(user -> ResponseEntity.ok(user))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyForLoan(@RequestBody LoanApplicationDTO application) {
        return ResponseEntity.ok(loanService.applyForLoan(application));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Loan>> getLoanStatus() {
        return ResponseEntity.ok(loanService.getUserLoans());
    }

}
