package org.tattour.server.domain.custom.domain;

import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.global.util.AuditingTimeEntity;

@Getter
@Table(name = "custom")
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Custom extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
    private List<CustomTheme> customThemes;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
    private List<CustomStyle> customStyles;

    @Column(name = "main_image_url", columnDefinition = "text")
    private String mainImageUrl;

    @Column(name = "hand_drawing_image_url", columnDefinition = "text")
    private String handDrawingImageUrl;

    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
    private List<CustomImage> images;

    @Column(name = "have_design", columnDefinition = "tinyint")
    private Boolean haveDesign;

    @Enumerated(value = EnumType.STRING)
    private CustomSize size;

    private String name;

    private String description;

    private String demand;

    private Integer count;

    @Column(name = "is_colored", columnDefinition = "tinyint")
    private Boolean isColored;

    @Column(name = "is_public", columnDefinition = "tinyint")
    private Boolean isPublic;

    @Column(name = "is_completed", columnDefinition = "tinyint")
    private Boolean isCompleted;

    @Enumerated(value = EnumType.STRING)
    private CustomProcess process;

    @Column(name = "view_count")
    private Integer viewCount;

    private Integer price;

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public void setImages(List<CustomImage> images) {
        this.images = images;
    }

    public void setHandDrawingImageUrl(String handDrawingImageUrl) {
        this.handDrawingImageUrl = handDrawingImageUrl;
    }

    public void setSize(CustomSize size) {
        this.size = size;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

//    public void setCount(Integer count) {
//        this.count = count;
//    }

//    public void setPublic(Boolean aPublic) {
//        isPublic = aPublic;
//    }

    public void setCustomProcess(CustomProcess process) {
        this.process = process;
    }

    public Integer calPrice() {
        Integer price = size.getPrice() * count;
        if (isPublic) {
            price -= size.getDiscountPrice();
        }
        return price;
    }

    public Boolean isNotSameUser(Integer userId) {
        return !user.getId().equals(userId);
    }

    public Boolean isNotAdmin(Integer userId) {
        return !userId.equals(1);
    }

    public static Custom of(
            User user,
            Boolean haveDesign,
            String name,
            String mainImageUrl,
            Boolean isCompleted,
            Integer viewCount) {
        return Custom.builder()
                .user(user)
                .haveDesign(haveDesign)
                .name(name)
                .mainImageUrl(mainImageUrl)
                .isCompleted(isCompleted)
                .viewCount(viewCount)
                .build();
    }

}
