package org.tattour.server.domain.point.domain;

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
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.user.domain.User;

@Entity
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointChargeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer chargeAmount;
    private Integer transferredAmount;
    @Column(columnDefinition = "tinyint")
    private Boolean isDeposited;
    @Column(columnDefinition = "tinyint")
    private Boolean isAmountMatched;
    @Column(columnDefinition = "tinyint")
    private Boolean isApproved;
    @Column(columnDefinition = "tinyint")
    private Boolean isCompleted;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;
    @Column(columnDefinition = "Timestamp")
    private String lastUpdatedAt;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private PointChargeRequest(Integer chargeAmount, User user) {
        this.chargeAmount = chargeAmount;
        this.user = user;
    }

    public static PointChargeRequest of(Integer chargeAmount, User user) {
        return new PointChargeRequest(chargeAmount, user);
    }

    public void setProperties(Integer transferredAmount, boolean isDeposited,
            boolean isAmountMatched, boolean isApproved, boolean isCompleted) {
        this.transferredAmount = transferredAmount;
        this.isDeposited = isDeposited;
        this.isAmountMatched = isAmountMatched;
        this.isApproved = isApproved;
        this.isCompleted = isCompleted;
    }
}
