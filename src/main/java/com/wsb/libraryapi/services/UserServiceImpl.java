package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.mappers.UserMapper;
import com.wsb.libraryapi.repositories.UserRepository;
import com.wsb.libraryapi.security.services.JwtService;
import io.jsonwebtoken.Jwt;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO me() {
        return userMapper.toDto(getCurrentUser());
    }

    @Override
    public UserDTO findUserById(String id) {
        return userMapper.toDto(
                userRepository.findById(UUID.fromString(id))
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal instanceof User ? (User) principal : null;
    }
}
