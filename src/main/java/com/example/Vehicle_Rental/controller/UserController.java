package com.example.Vehicle_Rental.controller;

import com.example.Vehicle_Rental.dtos.CreateUserRequest;
import com.example.Vehicle_Rental.model.User;
import com.example.Vehicle_Rental.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registers")
    public ResponseEntity<String> createUser(
            @Valid @RequestBody CreateUserRequest user) {
                userService.createUser(user);
            return ResponseEntity.ok("User registered successfully");
    }

}
