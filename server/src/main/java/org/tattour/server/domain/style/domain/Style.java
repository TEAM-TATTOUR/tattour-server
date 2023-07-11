package org.tattour.server.domain.style.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.sticker.domain.StickerStyle;

@Getter
@Table(name = "style")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;

    @OneToMany(mappedBy = "style")
    private List<StickerStyle> stickerStyles;

    @OneToMany(mappedBy = "style")
    private List<CustomStyle> customStyles;
}
