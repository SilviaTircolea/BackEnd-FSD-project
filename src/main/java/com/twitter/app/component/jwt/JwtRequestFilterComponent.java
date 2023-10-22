package com.twitter.app.component.jwt;

import com.twitter.app.model.user.User;
import com.twitter.app.service.jwtutil.JwtUtil;
import com.twitter.app.service.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Component("JwtRequestFilterComponent")
public class JwtRequestFilterComponent extends OncePerRequestFilter {

    private final Environment environment;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtRequestFilterComponent(Environment environment, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.environment = environment;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("authorization");
        String userId = null;
        String jwt = null;

        String applicationBearer = environment.getProperty("app.security.token.bearer");
        if (authorizationHeader != null && authorizationHeader.startsWith(applicationBearer)) {
            jwt = authorizationHeader.substring(applicationBearer.length());
            userId = jwtUtil.extractUsername(jwt);
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            User user = new User();
            user.setUserId(userDetails.getUsername());
            try {
                if (jwtUtil.validateToken(jwt, user)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
