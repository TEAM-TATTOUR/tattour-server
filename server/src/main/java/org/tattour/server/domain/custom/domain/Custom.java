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
import org.tattour.server.global.util.AuditingTimeEntity;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.User;

@Getter
@Table(name = "custom")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

	@OneToMany(mappedBy = "custom", cascade = CascadeType.ALL)
	private List<CustomImage> images;

	@Column(name = "have_design", columnDefinition = "tinyint")
	private Boolean haveDesign;

	private String size;

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
	private Process process;

	@Column(name = "view_count")
	private Integer viewCount;

	public static Custom from(User user, Sticker sticker, List<CustomTheme> customThemes,
		List<CustomStyle> customStyles, String mainImageUrl, List<CustomImage> images,
		Boolean haveDesign, String size, String name, String description, String demand,
		Integer count, Boolean isColored, Boolean isPublic, Boolean isCompleted, Process process,
		Integer viewCount) {
		return Custom.builder()
			.user(user)
			.sticker(sticker)
			.customThemes(customThemes)
			.customStyles(customStyles)
			.mainImageUrl(mainImageUrl)
			.images(images)
			.haveDesign(haveDesign)
			.size(size)
			.name(name)
			.description(description)
			.demand(demand)
			.count(count)
			.isColored(isColored)
			.isPublic(isPublic)
			.isCompleted(isCompleted)
			.process(process)
			.viewCount(viewCount)
			.build();
	}

}
