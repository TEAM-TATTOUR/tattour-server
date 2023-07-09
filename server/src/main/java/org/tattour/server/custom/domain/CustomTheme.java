package org.tattour.server.custom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.tattour.server.theme.domain.Theme;

@Table(name = "custom_theme")
@Entity
public class CustomTheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_id")
	private Custom custom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_id")
	private Theme theme;
}
