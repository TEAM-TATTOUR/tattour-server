package org.tattour.server.global.config.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.tattour.server.global.config.annotations.UserId;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

// HandlerMethodArgumentResolver 구현
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
    public Object resolveArgument(@NotNull MethodParameter parameter,
            ModelAndViewContainer modelAndViewContainer, @NotNull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String bearerHeader = request.getHeader("Authorization");

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
            return Integer.parseInt(tokenContents);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_CONTENT_EXCEPTION);
        }
    }
}