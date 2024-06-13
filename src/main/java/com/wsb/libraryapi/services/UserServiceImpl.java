package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.mappers.UserMapper;
import com.wsb.libraryapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public UserDTO findUserById(UUID id) {
        return userMapper.toDto(
                userRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal instanceof User ? (User) principal : null;
    }
}
