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
public class FormRequestDTO {
	
	private String module;
	
	private String submodule;
	
	//For backend use
	private String userType;

}
