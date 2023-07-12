package org.tattour.server.domain.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private String productSize;
    @Column(columnDefinition = "text")
    private String productImageUrl;
    private Integer productCount;
    private Integer productAmount;
    private Integer shippingFee;
    private Integer totalAmount;
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

    private Order(String productName, String productSize, String productImageUrl,
            Integer productCount,
            Integer productAmount, Integer shippingFee, Integer totalAmount, User user,
            Sticker sticker) {
        this.productName = productName;
        this.productSize = productSize;
        this.productImageUrl = productImageUrl;
        this.productCount = productCount;
        this.productAmount = productAmount;
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
        this.user = user;
        this.sticker = sticker;
    }

    public static Order of(String productName, String productSize, String productImageUrl,
            Integer productCount, Integer productAmount, Integer shippingFee, Integer totalAmount,
            User user, Sticker sticker){
        return new Order(productName, productSize, productImageUrl, productCount, productAmount, shippingFee, totalAmount, user, sticker);
    }
}
