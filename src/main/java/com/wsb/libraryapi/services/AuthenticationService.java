package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.authentication.AuthenticationRequest;
import com.wsb.libraryapi.dtos.authentication.AuthenticationResponse;
import com.wsb.libraryapi.dtos.authentication.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
