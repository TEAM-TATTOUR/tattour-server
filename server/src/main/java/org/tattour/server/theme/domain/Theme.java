package org.tattour.server.theme.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import org.tattour.server.custom.domain.CustomStyle;
import org.tattour.server.custom.domain.CustomTheme;
import org.tattour.server.sticker.domain.StickerStyle;
import org.tattour.server.sticker.domain.StickerTheme;

@Getter
@Table(name = "theme")
@Entity
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "theme")
	private List<StickerTheme> stickerThemes;

	@OneToMany(mappedBy = "theme")
	private List<CustomTheme> customThemes;
}
