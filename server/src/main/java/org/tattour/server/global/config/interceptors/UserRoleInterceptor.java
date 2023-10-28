package org.tattour.server.global.config.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tattour.server.domain.user.domain.UserRole;
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
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String bearerHeader = request.getHeader("Authorization");

        if (!jwtService.validateBearerHeader(bearerHeader)) {
            throw new BusinessException(ErrorType.NOT_SUPPORTED_JWT_TOKEN_EXCEPTION);
        }
        String token = bearerHeader.substring(HEADER_PREFIX.length());

        if (!jwtService.verifyToken(token)) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
        }

        final String roleValue = jwtService.getJwtContents(token).getRole();

        try {
            final UserRole role = UserRole.valueOf(roleValue);

            if (!role.equals(UserRole.ADMIN)) {
                throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_CONTENT_EXCEPTION);
        }
    }
}
