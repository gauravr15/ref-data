package com.odin.ref_data.service;

import javax.servlet.http.HttpServletRequest;

import com.odin.ref_data.dto.ResponseDTO;

public interface CountryService {

	ResponseDTO getCountryDetails(HttpServletRequest req, String filter);

}
