package com.portfolioargprg.lns.security.JWT;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTEntrypoint implements AuthenticationEntryPoint{
    private final static Logger logger = LoggerFactory.getLogger(JWTEntrypoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Falla de método commence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
