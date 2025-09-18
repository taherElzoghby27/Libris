package com.spring.boot.librarymanagementsystem.config.security;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.exception.ExpiredTokenException;
import com.spring.boot.librarymanagementsystem.service.UserService;
import com.spring.boot.librarymanagementsystem.setting.JWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenHandler {
    private final String secret;
    private final Duration time;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    @Autowired
    private UserService userService;

    public TokenHandler(JWTToken jwtToken) {
        this.secret = jwtToken.getSecret();
        this.time = jwtToken.getTime();
        Key key = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
        this.jwtBuilder = Jwts.builder().signWith(key);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    //generate token method
    public String generateToken(UserSystemDto userSystemDto) {
        this.jwtBuilder.setSubject(userSystemDto.getUsername());
        Date now = new Date();
        this.jwtBuilder.setIssuedAt(now);
        this.jwtBuilder.setExpiration(createExpirationDate(now));
        return this.jwtBuilder.compact();
    }

    //validate token
    public UserSystemDto validateToken(String token) {
        try {
            if (this.jwtParser.isSigned(token)) {
                Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
                String username = claims.getSubject();
                Date expirationDate = claims.getExpiration();
                Date issuedDate = claims.getIssuedAt();
                UserSystemDto userSystemDto = userService.getUserByUsername(username);
                boolean valid = expirationDate.after(new Date()) && issuedDate.before(expirationDate) && Objects.nonNull(userSystemDto);
                return valid ? userSystemDto : null;
            }
        } catch (Exception e) {
            throw new ExpiredTokenException(e.getMessage());
        }
        return null;
    }

    private Date createExpirationDate(Date date) {
        return Date.from(date.toInstant().plus(time));
    }
}
