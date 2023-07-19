package org.tattour.server.infra.sms.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageDto {

    private String to;
    private String content;

    public MessageDto(String to) {
        this.to = to;
    }
}