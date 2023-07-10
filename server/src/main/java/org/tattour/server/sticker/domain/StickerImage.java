package org.tattour.server.sticker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "sticker_image")
@Entity
public class StickerImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sticker_id")
	private Sticker sticker;

	@Column(name = "image_url", columnDefinition = "text")
	private String imageUrl;
}
