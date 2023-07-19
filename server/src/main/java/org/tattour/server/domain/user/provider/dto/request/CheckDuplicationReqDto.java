package org.tattour.server.domain.user.provider.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckDuplicationReqDto {

    Integer userId;
    Integer stickerId;

    public static CheckDuplicationReqDto of(Integer userId, Integer stickerId) {
        return new CheckDuplicationReqDto(userId, stickerId);
    }
}
