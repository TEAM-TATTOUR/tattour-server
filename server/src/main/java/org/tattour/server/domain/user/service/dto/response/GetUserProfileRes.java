package org.tattour.server.domain.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetUserProfileRes {
    private String name;
    private Integer point;
}
