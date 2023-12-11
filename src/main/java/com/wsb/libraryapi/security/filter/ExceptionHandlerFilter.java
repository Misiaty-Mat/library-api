package com.wsb.libraryapi.security.filter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException e) {
            sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "Cannot find a user with that username");
        } catch (AccessDeniedException | UsernameNotFoundException | BadCredentialsException e) {
            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "You cannot access this information");
        } catch (RuntimeException e) {
            sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "{\"error\": \"Unexpected data provided\"}");
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.getWriter().write(message);
        response.getWriter().flush();
    }

}
