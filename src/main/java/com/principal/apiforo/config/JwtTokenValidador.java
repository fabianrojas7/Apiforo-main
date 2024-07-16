package com.principal.apiforo.config;

import java.io.IOException;
import java.util.Collection;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.principal.apiforo.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenValidador extends OncePerRequestFilter  {

    private JwtUtils jwtUtils;

    public JwtTokenValidador(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            
            String jwtToken = token.substring(7);

            DecodedJWT decodedJWT = jwtUtils.validarToken(jwtToken);

            String usuario = jwtUtils.extractUsuario(decodedJWT);

            String rol = jwtUtils.claimEspecifico("rol", decodedJWT).asString();

            Collection<? extends GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(rol);

            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, roles);

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

        }
            
        filterChain.doFilter(request, response);
    }

    
    


}
