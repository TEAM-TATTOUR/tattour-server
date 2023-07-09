package org.tattour.server.custom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.tattour.server.style.domain.Style;

@Table(name = "custom_style")
@Entity
public class CustomStyle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_id")
	private Custom custom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "style_id")
	private Style style;
}
