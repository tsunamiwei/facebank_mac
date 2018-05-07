package com.pafacebank.facebank.filter;

import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BusinessAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Barer ")) {
            String businessTokenId = header.replace("Barer ", "");
            if (!tokenService.exists(businessTokenId)) {
                throw new AuthenticationException("token已失效，请重新进入交易");
            }
            BusinessToken businessToken = tokenService.get(businessTokenId, BusinessToken.class);
            if (!businessToken.isAuthenticated()) {
                throw new AuthenticationException("认证尚未完成，无法进行后续操作");
            }
            BusinessAuthentication businessAuthentication = new BusinessAuthentication(businessToken, null);
            SecurityContextHolder.getContext().setAuthentication(businessAuthentication);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}
