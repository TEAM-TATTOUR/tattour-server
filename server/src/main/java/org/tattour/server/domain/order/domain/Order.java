package org.tattour.server.domain.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

@Entity
@Table(name = "order_history")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productSize;
    @Column(columnDefinition = "text")
    private String productImageUrl;
    private Integer productAmount;
    private Integer productCount;
    private Integer shippingFee;
    private Integer totalAmount;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;
    private String status;
    @Column(columnDefinition = "Timestamp")
    private String createdAt;
    @Column(columnDefinition = "Timestamp")
    private String lastUpdatedAt;
    @Column(columnDefinition = "tinyint")
    private Boolean state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    public Order(String productName, String productSize, String productImageUrl, Integer productAmount,
            Integer productCount, Integer shippingFee, Integer totalAmount, String recipientName,
            String contact, String mailingAddress, String baseAddress, String detailAddress,
            User user, Sticker sticker) {
        this.productName = productName;
        this.productSize = productSize;
        this.productImageUrl = productImageUrl;
        this.productCount = productCount;
        this.productAmount = productAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.recipientName = recipientName;
        this.contact = contact;
        this.mailingAddress = mailingAddress;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.user = user;
        this.sticker = sticker;
    }

    public static Order of(String productName, String productSize, String productImageUrl, Integer productAmount,
            Integer productCount, Integer shippingFee, Integer totalAmount, String recipientName,
            String contact, String mailingAddress, String baseAddress, String detailAddress,
            User user, Sticker sticker){
        return new Order(productName, productSize, productImageUrl, productCount, productAmount,
                shippingFee, totalAmount, recipientName, contact, mailingAddress, baseAddress,
                detailAddress, user, sticker);
    }
}
