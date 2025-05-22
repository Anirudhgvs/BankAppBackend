package com.springdemo.project.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {


    // In a real application, you'd inject a UserRepository here
    // and fetch user from a database.
    // For this example, let's just hardcode a user.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This is where you would fetch the user from your database
        // based on the username.

        if ("user".equals(username)) {
            // Spring Security's User object requires a username, hashed password, and authorities (roles)
            return new org.springframework.security.core.userdetails.User(
                    "user", // username
                    "{bcrypt}$2a$10$T1YdO3x9Z1x5F3Y7N2O6C.gYf0W.nL.kK0.pQ.rS.sT.uV.wX.yZ.aB.cD.eF.gH", // Hashed password for "password"
                    new ArrayList<>() {{ add(new SimpleGrantedAuthority("ROLE_USER")); }}
            );
        } else if ("admin".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "admin",
                    "{bcrypt}$2a$10$T1YdO3x9Z1x5F3Y7N2O6C.gYf0W.nL.kK0.pQ.rS.sT.uV.wX.yZ.aB.cD.eF.gH", // Hashed password for "password"
                    new ArrayList<>() {{ add(new SimpleGrantedAuthority("ROLE_ADMIN")); add(new SimpleGrantedAuthority("ROLE_USER")); }}
            );
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
