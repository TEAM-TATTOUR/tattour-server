package org.tattour.server.domain.custom.service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomApplySummaryInfo {
    private Integer id;
    private String mainImageUrl;
    private String name;
    private String description;
    private String createdAt;
}
