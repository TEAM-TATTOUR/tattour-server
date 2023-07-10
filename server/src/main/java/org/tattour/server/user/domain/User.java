package org.tattour.server.user.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.tattour.server.user.service.dto.request.AddUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;

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
    private String loginType;
    @Column(columnDefinition = "Timestamp")
    private String created_at;
    @Column(columnDefinition = "Timestamp")
    private String last_updated_at;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductLiked> productLikeds;
    @Builder
    public User(String email, String loginType) {
        this.email = email;
        this.loginType = loginType;
    }

    //TODO : mapper로 할지 생각해보기
    public static User of(SaveUserReq req){
        return User.builder()
                .email(req.getEmail())
                .loginType(req.getLoginType())
                .build();
    }

    public void setUserInfo(AddUserInfoReq req){
        this.name = req.getName();
        this.phoneNumber = req.getPhoneNumber();
    }
}
