package org.tattour.server.domain.point.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.user.domain.User;

@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private PointLogCategory pointLogCategory;
    private String content;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;
    private Integer amount;
    private Integer resultPointAmount;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private UserPointLog(PointLogCategory pointLogCategory, String content, Integer amount,
            Integer resultPointAmount, User user) {
        this.pointLogCategory = pointLogCategory;
        this.content = content;
        this.amount = amount;
        this.resultPointAmount = resultPointAmount;
        this.user = user;
    }

    public static UserPointLog of(PointLogCategory pointLogCategory, String content, Integer amount,
            Integer resultPointAmount, User user) {
        return new UserPointLog(pointLogCategory, content, amount, resultPointAmount, user);
    }
}
