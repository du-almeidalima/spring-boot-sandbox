package com.dualmeidalima.sandbox.controllers;

import com.dualmeidalima.sandbox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getUsers(
            @PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var users = this.userRepository.findAllApprovedUsers(pageable);
        return ResponseEntity.ok(users);
    }
}
