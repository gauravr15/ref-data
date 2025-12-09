package com.odin.ref_data.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.ref_data.constants.APIConstants;
import com.odin.ref_data.constants.ApplicationConstants;
import com.odin.ref_data.dto.FormRequestDTO;
import com.odin.ref_data.dto.ResponseDTO;
import com.odin.ref_data.factory.FormFactory;

@RestController
@RequestMapping(value = APIConstants.API_VERSION + APIConstants.FORM)
public class FormMetadataController {

	@Autowired
	private FormFactory formService;

	@PostMapping
	public ResponseEntity<Object> getForm(HttpServletRequest req, @RequestBody FormRequestDTO formDTO) {
		String userType = req.getHeader(ApplicationConstants.USER_TYPE);
		ResponseDTO response = formService.getInstance(userType).getForm(req, formDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
