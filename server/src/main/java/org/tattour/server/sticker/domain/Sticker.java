package org.tattour.server.sticker.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.tattour.server.discount.domain.Discount;
import org.tattour.server.global.util.AuditingTimeEntity;

@Table(name = "sticker")
@Entity
public class Sticker extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private int price;
	private String composition;
	private int width;
	private int height;
	@Column(columnDefinition = "tinyint")
	private boolean state;

	@Column(name = "is_custom", columnDefinition = "tinyint")
	private boolean isCustom;

	@Column(name = "main_image_url", columnDefinition = "text")
	private String mainImageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discount_id")
	private Discount discount;

	@OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
	private List<StickerTheme> stickerThemes;

	@OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
	private List<StickerStyle> stickerStyles;

	@OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
	private List<StickerImage> images;

}
