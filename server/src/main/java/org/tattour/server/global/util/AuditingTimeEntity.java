package org.tattour.server.global.util;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingTimeEntity {

//	@CreatedDate
	@Column(columnDefinition = "Timestamp")
	private String createdAt;

//	@LastModifiedDate
	@Column(columnDefinition = "Timestamp")
	private String lastUpdatedAt;
}
