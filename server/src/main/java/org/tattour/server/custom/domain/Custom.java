package org.tattour.server.custom.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.tattour.server.global.util.AuditingTimeEntity;
import org.tattour.server.sticker.domain.Sticker;

@Table(name = "custom")
@Entity
public class Custom extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User user;
 */

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sticker_id")
	private Sticker sticker;

	@OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
	private List<CustomTheme> customThemes;

	@OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
	private List<CustomStyle> customStyles;

	@OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
	private List<CustomImage> images;

	@Column(name = "have_design", columnDefinition = "tinyint")
	private Boolean haveDesign;

	private String size;

	private String name;

	@Column(name = "main_image_url", columnDefinition = "text")
	private String mainImageUrl;

	private String description;

	private String demand;

	private Integer count;

	@Column(name = "is_colored", columnDefinition = "tinyint")
	private Boolean isColored;

	@Column(name = "is_public", columnDefinition = "tinyint")
	private Boolean isPublic;

	@Column(name = "is_completed", columnDefinition = "tinyint")
	private Boolean isCompleted;

//	@Enumerated(value = EnumType.STRING)
	private String process;

	@Column(name = "view_count")
	private Integer viewCount;
}
