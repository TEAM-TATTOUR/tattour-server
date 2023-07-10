package org.tattour.server.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetUserRes {
    private Integer id;
    private String name;
    private Integer point;
}
