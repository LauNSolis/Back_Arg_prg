package com.portfolioargprg.lns.security.JWT;

import com.portfolioargprg.lns.security.Service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;


public class JWTTokenFilter extends OncePerRequestFilter{
    private final static Logger logger = LoggerFactory.getLogger(JWTProvider.class);
    @Autowired
    JWTProvider jwtProvider;
    @Autowired
    UserDetailsImpl userDetailsImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if(token != null && jwtProvider.validateToken(token)) {
               String nombredeUsuario = jwtProvider.getNombredeUsuarioFromTocken(token);
               UserDetails userDetails =  userDetailsImpl.loadUserByUsername(nombredeUsuario);
               UsernamePasswordAuthenticationToken auth;
                auth = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);
            } 
        } catch (Exception e) {
                    logger.error("Falló el método doFilterInternal");
        }
        filterChain.doFilter(request, response);
    }  
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer", "");
        return null;
    }

}
