package com.project.eCommerce.Controller;

import com.project.eCommerce.Security.JwtUtil;
import com.project.eCommerce.Service.CustomUserDetailsService;
import com.project.eCommerce.dto.AuthRequest;
import com.project.eCommerce.dto.LoginRequest;
import com.project.eCommerce.entity.User;
import com.project.eCommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {


            // 1. Find user by email
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 2. Validate password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            // 3. Generate JWT token
            String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()
            ));


            return ResponseEntity.ok(token);
        }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req){
        if(userRepository.findByEmail(req.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body("Email already Exists");
        }
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }
}
