package org.tattour.server.domain.order.model;

import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.user.model.User;

@Entity
@Table(name = "order_history")
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productAmount;
    private Integer shippingFee;
    private Integer totalAmount;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(columnDefinition = "Timestamp")
    private String createdAt;

    @Column(columnDefinition = "Timestamp")
    private String lastUpdatedAt;

    @Column(columnDefinition = "tinyint")
    private Boolean state;

    @OneToMany(mappedBy = "orderHistory")
    private List<OrderedProduct> orderedProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public OrderHistory(Integer productAmount, Integer shippingFee, Integer totalAmount,
            String recipientName,
            String contact, String mailingAddress, String baseAddress, String detailAddress,
            User user) {
        this.productAmount = productAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.recipientName = recipientName;
        this.contact = contact;
        this.mailingAddress = mailingAddress;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.user = user;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
