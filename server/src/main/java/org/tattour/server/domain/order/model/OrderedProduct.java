package org.tattour.server.domain.order.model;

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
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.sticker.model.Sticker;

@Entity
@Table(name = "ordered_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer count;
    @Column(columnDefinition = "text")
    private String mainImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_history_id")
    private OrderHistory orderHistory;

    @Builder
    private OrderedProduct(String name, Integer price, Integer count, String mainImageUrl, Sticker sticker,
                           OrderHistory orderHistory) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.mainImageUrl = mainImageUrl;
        this.sticker = sticker;
        this.orderHistory = orderHistory;
    }
}
