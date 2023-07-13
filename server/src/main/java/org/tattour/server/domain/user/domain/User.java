package org.tattour.server.domain.user.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@Entity
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Integer point;
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private SocialPlatform socialPlatform;
    @Column(columnDefinition = "Timestamp")
    private String created_at;
    @Column(columnDefinition = "Timestamp")
    private String last_updated_at;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductLiked> productLikeds;
    @Builder
    public User(String email, SocialPlatform socialPlatform, String accessToken, String refreshToken) {
        this.email = email;
        this.socialPlatform = socialPlatform;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    //TODO : mapper로 할지 생각해보기
    public static User of(SaveUserReq req){
        return User.builder()
                .email(req.getEmail())
                .socialPlatform(req.getSocialPlatform())
                .accessToken(req.getAccessToken())
                .refreshToken(req.getRefreshToken())
                .build();
    }

    public void setUserInfo(UpdateUserInfoReq req){
        this.name = req.getName();
        this.phoneNumber = req.getPhoneNumber();
    }

    public void setUserPoint(int amount){
        this.point = amount;
    }

    public void deleteToken(){
        this.accessToken = null;
        this.refreshToken = null;
    }
}
