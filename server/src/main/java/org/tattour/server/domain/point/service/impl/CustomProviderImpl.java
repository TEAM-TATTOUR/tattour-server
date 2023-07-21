package org.tattour.server.domain.point.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.exception.NotFoundCustomException;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.custom.repository.impl.CustomRepositoryImpl;
import org.tattour.server.domain.custom.service.dto.request.GetCustomSummaryInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.custom.service.dto.response.CustomSummaryList;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.UnauthorizedException;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class CustomProviderImpl implements CustomProvider {
    private final CustomRepositoryImpl customRepository;
    private final UserServiceImpl userService;

    @Override
    @Transactional(readOnly = true)
    public Custom getCustomById(Integer customId, Integer userId) {
        Custom custom = customRepository.findById(customId)
                .orElseThrow(NotFoundCustomException::new);
        User user = userService.getUserByUserId(userId);
        if (!custom.getUser().equals(user) && !userId.equals(1)) {
            throw new UnauthorizedException();
        }
        return custom;
    }

    @Override
    public CustomApplySummaryInfoList getCustomApplySummaryInfoList(GetCustomSummaryInfo req) {
        List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(
                req.getUserId());
        return CustomApplySummaryInfoList.of(
                EntityDtoMapper.INSTANCE.toCustomApplySummaryInfoList(customs));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomSummaryList getCustomSummaryCompleteListByUserId(Integer userId) {
        List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedTrue(userId);
        return CustomSummaryList.of(customs);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomSummaryList getCustomSummaryInCompleteListByUserId(Integer userId) {
        List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(userId);
        return CustomSummaryList.of(customs);
    }
}
