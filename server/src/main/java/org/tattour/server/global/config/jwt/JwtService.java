package org.tattour.server.global.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.exception.UnauthorizedException;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-expired}")
    private Integer accessExpired;
    private static final String HEADER_PREFIX = "Bearer ";

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder()
                .encodeToString(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String issuedToken(Integer userId, String role) {
        String strUserId = String.valueOf(userId);
        final Date now = new Date();

        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now)
                .setExpiration(Date.from(Instant.now().plus(accessExpired, ChronoUnit.MINUTES)));

        claims.put("userId", strUserId);
        claims.put("role", role);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {
            if (e instanceof ExpiredJwtException) {
                throw new UnauthorizedException(ErrorType.TOKEN_TIME_EXPIRED_EXCEPTION);
            }
            return false;
        }
    }

    private Key getSigningKey() {
        final byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public JwtContent getJwtContents(String token) {
        final Claims claims = getBody(token);
        return JwtContent.of(claims.get("userId"), claims.get("role"));
    }

    public String getTokenFromHeader(String bearerHeader) {
        validateBearerHeader(bearerHeader);
        return bearerHeader.substring(HEADER_PREFIX.length());
    }

    public void validateBearerHeader(String bearerHeader) {
        if (!StringUtils.hasText(bearerHeader) || !bearerHeader.startsWith(HEADER_PREFIX)) {
            throw new BusinessException(ErrorType.NOT_SUPPORTED_JWT_TOKEN_EXCEPTION);
        }
    }
}