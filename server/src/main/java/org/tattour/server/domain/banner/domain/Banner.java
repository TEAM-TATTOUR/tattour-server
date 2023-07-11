package org.tattour.server.domain.banner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.global.util.AuditingTimeEntity;

@Getter
@Table(name = "banner")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "image_url",columnDefinition = "text")
	private String imageUrl;
}
