package org.tattour.server.discount.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.tattour.server.global.util.AuditingTimeEntity;
import org.tattour.server.sticker.domain.Sticker;

@Table(name = "discount")
@Entity
public class Discount extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	private List<Sticker> stickers;

	@Column(name = "discount_rate")
	private int discountRate;

	@Column(name = "start_at")
	private LocalDateTime startAt;

	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	@Column(name = "is_ended", columnDefinition = "tinyint")
	private boolean isEnded;
}
