package com.company.security.jwt.controller;

import com.company.security.jwt.model.RegisterUser;
import com.company.security.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



@RestController
@RequestMapping("/auth")
public  class  AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterUser registerUser) {
        //age custom logic dashtim
        //mede check kardan user ...

        userService.saveUser(registerUser);
        return ResponseEntity.ok().build();
    }
}
