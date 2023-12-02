package org.tattour.server.global.config.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tattour.server.domain.user.domain.UserRole;
import org.tattour.server.global.config.jwt.JwtContent;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Component
@RequiredArgsConstructor
public class AdminRoleInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        String bearerHeader = request.getHeader("Authorization");
        String token = jwtService.getTokenFromHeader(bearerHeader);

        if (!jwtService.verifyToken(token)) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
        }

        final JwtContent content = jwtService.getJwtContents(token);

        try {
            System.out.println("content.getRole() = " + content.getRole());

            final UserRole role = UserRole.valueOf(content.getRole());

            if (!role.equals(UserRole.ADMIN)) {
                throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
            }
            return true;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_CONTENT_EXCEPTION);
        }
    }
}
