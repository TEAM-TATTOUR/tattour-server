package org.tattour.server.style.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.tattour.server.custom.domain.CustomStyle;
import org.tattour.server.sticker.domain.StickerStyle;

@Table(name = "style")
@Entity
public class Style {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "style")
	private List<StickerStyle> stickerStyles;

	@OneToMany(mappedBy = "style")
	private List<CustomStyle> customStyles;
}