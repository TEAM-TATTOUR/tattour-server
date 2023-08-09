package org.tattour.server.domain.custom.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.exception.NotFoundCustomException;
import org.tattour.server.domain.custom.facade.dto.request.ReadCustomSummaryRes;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.domain.custom.facade.dto.response.CreateCustomSummaryListRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.UnauthorizedException;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class CustomProviderImpl implements CustomProvider {

    private final CustomRepository customRepository;
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
    public CreateCustomSummaryListRes getCustomApplySummaryInfoList(ReadCustomSummaryRes req) {
        List<Custom> customs =
                customRepository.findAllByUser_IdAndIsCompletedFalse(req.getUserId());
        return CreateCustomSummaryListRes.from(
                EntityDtoMapper.INSTANCE.toCustomApplySummaryInfoList(customs));
    }

    @Override
    @Transactional(readOnly = true)
    public ReadCustomSummaryListRes getCustomSummaryCompleteListByUserId(Integer userId) {
        List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedTrue(userId);
        return ReadCustomSummaryListRes.from(customs);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadCustomSummaryListRes getCustomSummaryInCompleteListByUserId(Integer userId) {
        List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(userId);
        return ReadCustomSummaryListRes.from(customs);
    }
}
