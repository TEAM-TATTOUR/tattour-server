package org.tattour.server.global.util;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class AuditingTimeEntity {

	@Column(columnDefinition = "Timestamp")
	private String createdAt;

	@Column(columnDefinition = "Timestamp")
	private String lastUpdatedAt;
}
