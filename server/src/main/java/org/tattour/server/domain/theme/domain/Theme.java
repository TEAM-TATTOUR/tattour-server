package org.tattour.server.domain.theme.domain;

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
import org.tattour.server.domain.custom.domain.CustomTheme;
import org.tattour.server.domain.sticker.domain.StickerTheme;

@Getter
@Table(name = "theme")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Column(columnDefinition = "text")
    private String imageUrl;

    @OneToMany(mappedBy = "theme")
    private List<StickerTheme> stickerThemes;

    @OneToMany(mappedBy = "theme")
    private List<CustomTheme> customThemes;
}
