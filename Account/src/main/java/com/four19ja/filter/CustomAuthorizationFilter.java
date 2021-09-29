package com.four19ja.filter;

import com.four19ja.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final transient JwtConfig jwtConfig;

    public CustomAuthorizationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/auth") || request.getServletPath().equals("/security/token/refresh")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(jwtConfig.getHeader());
            if(authorizationHeader != null && authorizationHeader.startsWith(jwtConfig.getPrefix())) {
                try {
                    String token = authorizationHeader.substring(jwtConfig.getPrefix().length());
                    Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(jwtConfig.getSecret()).build().parse(token).getBody();

                    String username = claims.getSubject();

                    if (username != null) {
                        @SuppressWarnings("unchecked")
                        List<String> authorities = (List<String>) claims.get("authorities");
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                username, null, authorities.stream().map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        filterChain.doFilter(request, response);
                    }
                }catch (ExpiredJwtException exception) {
                    response.setHeader("Access-Control-Expose-Headers", "error");
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    response.sendError(FORBIDDEN.value());
                }
                catch (Exception exception) {
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    response.sendError(FORBIDDEN.value());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
