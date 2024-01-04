package com.wsb.libraryapi.security;

import com.wsb.libraryapi.authorities.Role;
import com.wsb.libraryapi.security.filter.JWTAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.wsb.libraryapi.security.SecurityConstants.AUTH_WHITELABEL_PATH;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requestManager -> {
            requestManager.requestMatchers(AUTH_WHITELABEL_PATH).permitAll();
            requestManager.requestMatchers(HttpMethod.GET, "/api/v1/user/list").hasAuthority(Role.ADMIN.name());
            requestManager.requestMatchers(HttpMethod.GET, "/api/v1/loans").hasAuthority(Role.ADMIN.name());
            requestManager.requestMatchers(HttpMethod.GET, "/api/v1/books/**").permitAll();
            requestManager.anyRequest().authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configure(http));
        http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}