package com.company.security.jwt.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    //inam vase moghe ke karbar khast be chizi ke permission nadare req bezane
    // :/
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        e = new AccessDeniedException("shoma be in address dastrasi nadari");

        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());

    }
}
