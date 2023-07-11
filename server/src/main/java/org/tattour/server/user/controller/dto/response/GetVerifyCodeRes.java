package org.tattour.server.user.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetVerifyCodeRes {
    private boolean isVerified;

    public GetVerifyCodeRes(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public static GetVerifyCodeRes of(boolean isVerified){
        return new GetVerifyCodeRes(isVerified);
    }
}
