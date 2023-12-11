package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.authentication.AuthenticationRequest;
import com.wsb.libraryapi.dtos.authentication.AuthenticationResponse;
import com.wsb.libraryapi.dtos.authentication.RegisterRequest;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);

    public  AuthenticationResponse authenticate(AuthenticationRequest request);
}
