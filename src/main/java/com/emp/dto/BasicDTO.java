package com.emp.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class BasicDTO {

    private Long id;


    private Integer version;


    private Timestamp lastModifiedDate;
}
