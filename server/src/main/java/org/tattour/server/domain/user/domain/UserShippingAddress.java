package org.tattour.server.domain.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private UserShippingAddress(String recipientName, String contact, String mailingAddress,
            String baseAddress, String detailAddress, User user) {
        this.recipientName = recipientName;
        this.contact = contact;
        this.mailingAddress = mailingAddress;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.user = user;
    }

    public static UserShippingAddress of(String recipientName, String contact, String mailingAddress,
            String baseAddress, String detailAddress, User user){
        return new UserShippingAddress(recipientName, contact, mailingAddress, baseAddress, detailAddress, user);
    }
}
