package com.odin.ref_data.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odin.ref_data.constants.APIConstants;
import com.odin.ref_data.dto.ResponseDTO;
import com.odin.ref_data.service.CountryService;

@RestController
@RequestMapping(value = APIConstants.API_VERSION)
public class CountryController {
	
	@Autowired
	private CountryService service;
	
	@GetMapping(APIConstants.GET_COUNTRIES+ APIConstants.FILTER)
	public ResponseEntity<Object> getCountry(HttpServletRequest req, @PathVariable String filter) {
		ResponseDTO response = service.getCountryDetails(req, filter);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
