package com.bsofts.fidelity.controllers;


import com.bsofts.fidelity.models.Notifications;
import com.bsofts.fidelity.models.User;
import com.bsofts.fidelity.payload.requests.LoginRequest;
import com.bsofts.fidelity.payload.responses.LoginResponse;
import com.bsofts.fidelity.repositories.NotificationsRepository;
import com.bsofts.fidelity.repositories.UserRepository;
import com.bsofts.fidelity.security.jwt.JwtTokenUtils;
import com.bsofts.fidelity.services.AuthService; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    AuthService authService;
    
    @Autowired
    JwtTokenUtils jwtProvider;
    
    @Autowired
    UserRepository userTepository;
    
    @Autowired
    NotificationsRepository notificationsRepository;

    // API Se connecter
    @PostMapping("/login/")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        String token = this.authService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token,"Bearer", "Login successfully"));
    }
    
    
    @GetMapping("/info")
    public User   getCurrentUser(HttpServletRequest req) {
        User current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String email=this.jwtProvider .getUsernameFromToken(token); 
        System.out.print(email); 
        current=this.userTepository.findByEmail(email);
        
        
        
        return current;
        
    }
}
