package org.example.nobs.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nobs.utils.JWTUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //token will be  sent in the header of the request
        //Bearer [jwt]

        String authHeader = request.getHeader("Authorization");

        System.out.println(authHeader);

        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // cut off bearer & left with JWT

        }
        //Valid token
        if (token !=null && JWTUtils.validateToken(token)){
            // trích xuất tên người dùng
            String username = JWTUtils.extractUsername(token);

            //tạo đối tượng xác thực biểu diễn thông tin liên quan đến người dùng
            Authentication auth = new UsernamePasswordAuthenticationToken(username,null, Collections.emptyList());
            // người dùng đc xác thực và có quyền truy cập vào tài nguyên ứng dụng
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);

    }
}
