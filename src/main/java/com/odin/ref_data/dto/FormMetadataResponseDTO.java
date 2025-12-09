package com.odin.ref_data.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormMetadataResponseDTO {

	private Integer id;

	private String module;

	private String displayName;

	private String placeholder;

	private String fieldName;

	private Boolean isActive;

	private Boolean adminEditable;

	private Boolean userEditable;

	private Timestamp creationTimestamp;

	private Timestamp updateTimestamp;

	private String dataType;

	private String userType;

	private String submodule;

	private Long sequence;
	
	private Boolean isDisplay;
    
    private Boolean isDropdown;
    
    private String regex;
    
    private String inputType;
    
    private String resource;
}
