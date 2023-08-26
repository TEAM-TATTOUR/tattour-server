package org.tattour.server.global.config.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tattour.server.domain.user.provider.UserProvider;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Component
@RequiredArgsConstructor
public class UserRoleInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;
    private final UserProvider userProvider;
    private static final String HEADER_PREFIX = "Bearer ";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        String bearerHeader = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerHeader) || !bearerHeader.startsWith(HEADER_PREFIX)) {
            throw new BusinessException(ErrorType.NOT_SUPPORTED_JWT_TOKEN_EXCEPTION);
        }
        String token = bearerHeader.substring(HEADER_PREFIX.length());

        // 토큰 검증
        if (!jwtService.verifyToken(token)) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(token);

        try {
            final Integer userId = Integer.parseInt(tokenContents);

            return userProvider.isUserAdmin(userProvider.readUserById(userId));
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_CONTENT_EXCEPTION);
        }
    }
}
