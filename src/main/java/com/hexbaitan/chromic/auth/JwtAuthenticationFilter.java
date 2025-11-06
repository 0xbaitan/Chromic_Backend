package com.hexbaitan.chromic.auth;

import com.hexbaitan.chromic.users.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtCodec jwtCodec;

    private final UserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtCodec jwtCodec, CustomUserDetailsService customUserDetailsService) {
        this.jwtCodec = jwtCodec;
        this.customUserDetailsService = customUserDetailsService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) {

        String token = extractJwtTokenFromRequest(request);
        String emailId = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (Objects.nonNull(token)) {
            emailId = jwtCodec.validateToken(token);

        }

        if (Objects.nonNull(emailId) && Objects.isNull(securityContext.getAuthentication())) {
            UserDetails details = customUserDetailsService.loadUserByUsername(emailId);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
            System.out.println(context.getAuthentication().getPrincipal());
        }

        filterchain.doFilter(request, response);
    }

    private String extractJwtTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        return token;
    }

}
