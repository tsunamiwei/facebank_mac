package com.pafacebank.facebank.filter;

import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.JwtUtils;
import com.pafacebank.facebank.util.UserAuthentication;
import com.pafacebank.facebank.util.UserContext;
import com.pafacebank.facebank.util.UserToken;
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
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Fb-User ")) {
            UserToken currentUser = JwtUtils.convertToUser(header.replace("Fb-User ", ""));
            if (!tokenService.exists(currentUser.getTokenId())) {
                throw new AuthenticationException("token已失效，请重新登陆");
            } else {
                tokenService.refresh(currentUser.getTokenId());
            }
            UserContext.setCurrentUser(currentUser);
            UserAuthentication userAuthentication = new UserAuthentication(currentUser, null);
            userAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}
