package com.springdemo.project.Service;

import com.springdemo.project.Entity.Role;
import com.springdemo.project.Entity.User;
import com.springdemo.project.Repositories.UserRepo;
import com.springdemo.project.Utils.AuthUtil;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private AuthUtil authUtil;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        createAdminUser();
    }

    private void createAdminUser() {
        if (userRepo.findByUsername("admin") == null) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("admin@bank.com")
                    .role(Role.ADMIN)
                    .fullName("System Administrator")
                    .address("Bank Headquarters")
                    .phoneNumber("+1-555-000-0000")
                    .monthlyIncome(10000.0)
                    .employmentStatus("Full-Time")
                    .build();
            userRepo.save(admin);
        }
    }

    public User getCurrentUserProfile() {
        return authUtil.getCurrentUser();
    }

    public List<User> getAllusers() {
        return userRepo.findAll();
    }

    public Boolean createUser(User userEntry) {
        try {
            // Ensure this is treated as a new entity
            userEntry.setId(null);
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userRepo.save(userEntry);
            return true;
        } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
            // Handle concurrent modification
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // public UserEntry createAdminUser(UserEntry userEntry) {
    // userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
    // userEntry.setRoles(Collections.singletonList("ADMIN"));
    // }

    public Optional<User> getByUserName(String userName) {
        return Optional.ofNullable(userRepo.findByUsername(userName));
    }

    public Object updateUser(User userEntry1) {
        return userRepo.save(userEntry1);
    }

    public void deleteUserByUserName(String userName) {
        userRepo.deleteByUsername(userName);
    }
}
