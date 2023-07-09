package org.tattour.server.sticker.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.tattour.server.style.domain.Style;

@Table(name = "sticker_style")
@Entity
public class StickerStyle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sticker_id")
	private Sticker sticker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "style_id")
	private Style style;
}
