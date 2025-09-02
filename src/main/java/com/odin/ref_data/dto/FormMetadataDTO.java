package com.odin.ref_data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormMetadataDTO {
	
	private Long id;
	private String displayName;
	private String placeholder;
	private String fieldName;
	private Boolean adminEditable;
	private Boolean userEditable;
	private String dataType;
	private Long sequence;

}
