package com.four19ja.controllers;

import com.four19ja.entities.User;
import com.four19ja.entities.UserRole;
import com.four19ja.security.JwtConfig;
import com.four19ja.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Controller
@RequestMapping(path = "/security")
public class SecurityController {
    private UserService userService;
    private JwtConfig jwtConfig;

    public SecurityController(UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(jwtConfig.getHeader());
        if(authorizationHeader != null && authorizationHeader.startsWith(jwtConfig.getPrefix())) {
            try{
                String token = authorizationHeader.substring(jwtConfig.getPrefix().length());
                Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(jwtConfig.getSecret()).build().parse(token).getBody();
                String username = claims.getSubject();
                User user = userService.getUserByUsername(username);
                Long now = System.currentTimeMillis();
                SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));
                String access_token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .claim("authorities", userService.getUserRoles(user.getId()).stream()
                                .map(UserRole::getUserID).collect(Collectors.toList()))
                        .setIssuedAt(new java.sql.Date(now))
                        .setExpiration(new java.sql.Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();
                String refresh_token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .setIssuedAt(new java.sql.Date(now))
                        .setExpiration(new java.sql.Date(now + jwtConfig.getRefreshExpiration() * 1000))  // in milliseconds #TODO: make expiration for refresh different
                        .signWith(key, SignatureAlgorithm.HS512)
                        .compact();
                response.setHeader("access_token", access_token);
                response.setHeader("refresh_token", refresh_token);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                response.sendError(FORBIDDEN.value());
            }
        }
    }
}
