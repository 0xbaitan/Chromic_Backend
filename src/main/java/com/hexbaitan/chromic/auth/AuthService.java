package com.hexbaitan.chromic.auth;


import com.hexbaitan.chromic.auth.dto.LoginRequestDto;
import com.hexbaitan.chromic.users.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationProvider daoAuthenticationProvider;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtCodec jwtCodec;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationProvider daoAuthenticationProvider, CustomUserDetailsService customUserDetailsService, JwtCodec jwtCodec, PasswordEncoder passwordEncoder) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtCodec = jwtCodec;
        this.passwordEncoder = passwordEncoder;
    }


    public String login(LoginRequestDto dto) {

        daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(dto.username());
        return jwtCodec.generateToken(userDetails);
    }
}
