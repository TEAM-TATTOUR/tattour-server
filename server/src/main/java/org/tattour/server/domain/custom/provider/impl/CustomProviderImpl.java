package org.tattour.server.domain.custom.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.exception.NotFoundCustomException;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.global.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class CustomProviderImpl implements CustomProvider {

    private final CustomRepository customRepository;

    @Override
    @Transactional(readOnly = true)
    public Custom getCustomById(Integer customId, Integer userId) {
        Custom custom = customRepository.findById(customId)
                .orElseThrow(NotFoundCustomException::new);
        if (isNotAdmin(userId)) {
            if (custom.isNotSameUser(userId)) {
                throw new UnauthorizedException();
            }
        }
        return custom;
    }

    // Todo : refactoring
    boolean isNotAdmin(Integer userId) {
        return userId != 1;
    }

    @Override
    public List<Custom> getCustomByUserIdAfterDate(int userId, String date) {
        return customRepository.findAllByUser_IdAndCreatedAtAfter(userId, date);
    }
}
