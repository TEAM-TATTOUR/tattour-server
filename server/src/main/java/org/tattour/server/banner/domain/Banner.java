package org.tattour.server.banner.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.tattour.server.global.util.AuditingTimeEntity;

@Table(name = "banner")
@Entity
public class Banner extends AuditingTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "image_url",columnDefinition = "text")
	private String imageUrl;
}
