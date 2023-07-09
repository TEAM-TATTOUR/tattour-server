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
import org.tattour.server.golbal.util.AuditingTimeEntity;
import org.tattour.server.sticker.domain.Sticker;

@Table(name = "custom")
@Entity
public class Custom extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// user, sticker  추가
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


	@Column(name = "hane_design")
	private boolean haveDesign;

	private String size;

	private String name;

	@Column(name = "main_image_url")
	private String mainImageUrl;

	private String description;

	private String demand;

	private int count;

	@Column(name = "is_colored")
	private boolean isColored;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "is_completed")
	private boolean isCompleted;

//	@Enumerated(value = EnumType.STRING)
	private String process;

	@Column(name = "view_count")
	private int viewCount;
}
