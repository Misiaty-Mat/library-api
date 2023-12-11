package com.wsb.libraryapi.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "77fc29131d4acbf0f291c99b79897c956f974d8ffb7125db41f9fdd8e54b1dd2"; //Your secret should always be strong (uppercase, lowercase, numbers, symbols) so that nobody can potentially decode the signature.
    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTH_WHITELABEL_PATH = "/api/v1/auth/**"; // Public path that clients can use to register.
}
