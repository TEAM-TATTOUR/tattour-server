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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.sticker.domain.Sticker;

@Entity
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductLiked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    @Builder
    public ProductLiked(User user, Sticker sticker) {
        this.user = user;
        this.sticker = sticker;
    }

    public static ProductLiked of(User user, Sticker sticker) {
        return ProductLiked.builder()
                .user(user)
                .sticker(sticker)
                .build();
    }
}
