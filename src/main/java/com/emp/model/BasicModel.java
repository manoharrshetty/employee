
package com.emp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BasicModel {


	@Version
	@Column(name = "version")
	private Integer version;
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Timestamp lastModifiedDate;
}
