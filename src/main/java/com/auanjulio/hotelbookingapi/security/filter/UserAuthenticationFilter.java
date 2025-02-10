package com.auanjulio.hotelbookingapi.security.filter;

import com.auanjulio.hotelbookingapi.dao.usuario.TabUsuarioObj;
import com.auanjulio.hotelbookingapi.dao.usuario.repository.TabUsuarioRepository;
import com.auanjulio.hotelbookingapi.security.UserDetailsImpl;
import com.auanjulio.hotelbookingapi.security.configuration.SecurityConfiguration;
import com.auanjulio.hotelbookingapi.security.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveyToken(request);
            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);
                TabUsuarioObj tabUsuarioObj = tabUsuarioRepository.findByTxEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(tabUsuarioObj);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Token inválido");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoveyToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private Boolean checkIfEndpointIsNotPublic (HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTH_NOT_REQUIRED).contains(requestUri);  
    }
}
