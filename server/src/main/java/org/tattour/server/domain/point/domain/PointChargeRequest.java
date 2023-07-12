package org.tattour.server.domain.point.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.user.domain.User;

@Entity
@DynamicInsert
public class PointChargeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer chargeAmount;
    @Column(columnDefinition = "tinyint")
    private Boolean isDeposited;
    private Integer transferredAmount;
    @Column(columnDefinition = "tinyint")
    private Boolean isAmountMatched;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;
    @Column(columnDefinition = "Timestamp")
    private String lastUpdatedAt;
    @Column(columnDefinition = "tinyint")
    private Boolean isCompleted;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
