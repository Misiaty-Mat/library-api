package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.mappers.UserMapper;
import com.wsb.libraryapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO findUserById(String id) {
        UserDTO foundUser = userMapper.toDto(
                userRepository.findById(UUID.fromString(id))
                        .orElseThrow(EntityNotFoundException::new)
        );
        foundUser.setPassword(null);
        return foundUser;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        UserDTO createdUser = userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
        createdUser.setPassword(null);
        return createdUser;
    }
}
