package com.example.SubscriptionManagementSystem.Filter;

import com.example.SubscriptionManagementSystem.Configuration.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                username = jwtUtil.extractUsername(token);
            }

            System.out.println(token);
            System.out.println(username);

            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println(userDetails.getUsername());
                System.out.println(jwtUtil.validToken(token,userDetails.getUsername()));
                if(jwtUtil.validToken(token,userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    System.out.println(authenticationToken);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request,response);
        }catch (ExpiredJwtException expiredJwtException){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write("JWT Token Expired");
            response.getWriter().flush();
            return;
        }
    }
}
