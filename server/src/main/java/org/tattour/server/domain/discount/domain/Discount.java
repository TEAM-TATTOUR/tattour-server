package org.tattour.server.domain.discount.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.tattour.server.global.util.AuditingTimeEntity;

@Table(name = "discount")
@Entity
@Builder
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Discount extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "discount_rate")
	private Integer discountRate;

	@Column(name = "start_at", columnDefinition = "Timestamp")
	private LocalDateTime startAt;

	@Column(name = "expired_at", columnDefinition = "Timestamp")
	private LocalDateTime expiredAt;

	@Column(name = "is_ended", columnDefinition = "tinyint")
	private Boolean isEnded;

	public static Discount of(
			String name,
			Integer discountRate,
			LocalDateTime startAt,
			LocalDateTime expiredAt,
			Boolean isEnded) {
		return Discount.builder()
				.name(name)
				.discountRate(discountRate)
				.startAt(startAt)
				.expiredAt(expiredAt)
				.isEnded(isEnded)
				.build();
	}
}
