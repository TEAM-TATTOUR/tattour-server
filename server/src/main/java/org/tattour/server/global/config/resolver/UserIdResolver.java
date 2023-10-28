package org.tattour.server.global.config.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.tattour.server.domain.user.domain.UserRole;
import org.tattour.server.global.config.annotations.UserId;
import org.tattour.server.global.config.jwt.JwtContent;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;
    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) && Integer.class.equals(
                parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            @NotNull MethodParameter parameter,
            ModelAndViewContainer modelAndViewContainer,
            @NotNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String bearerHeader = request.getHeader("Authorization");

        if (!jwtService.validateBearerHeader(bearerHeader)) {
            throw new BusinessException(ErrorType.NOT_SUPPORTED_JWT_TOKEN_EXCEPTION);
        }

        String token = bearerHeader.substring(HEADER_PREFIX.length());

        if (!jwtService.verifyToken(token)) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
        }

        final JwtContent content = jwtService.getJwtContents(token);

        try {
            UserRole role = UserRole.valueOf(content.getRole());
            Integer userId = Integer.parseInt(content.getUserId());

            if (!role.equals(UserRole.USER)) {
                throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
            }

            return userId;
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_CONTENT_EXCEPTION);
        }
    }
}