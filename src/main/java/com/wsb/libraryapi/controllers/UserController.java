package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        return ResponseEntity.ok(userService.me());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
