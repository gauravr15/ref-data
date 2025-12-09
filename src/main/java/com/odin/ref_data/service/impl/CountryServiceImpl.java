package com.odin.ref_data.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odin.ref_data.constants.LanguageConstants;
import com.odin.ref_data.constants.ResponseCodes;
import com.odin.ref_data.dto.ResponseDTO;
import com.odin.ref_data.entity.CountryConfigurationModel;
import com.odin.ref_data.repo.CountryConfigurationRepository;
import com.odin.ref_data.service.CountryService;
import com.odin.ref_data.utility.ResponseObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private ResponseObject response;

	@Autowired
	private CountryConfigurationRepository countryRepo;

	@Override
	public ResponseDTO getCountryDetails(HttpServletRequest req, String filter) {
		log.info("request received for filter : {}", filter);
		if (filter.equalsIgnoreCase("ALL")) {
			return response.buildResponse(LanguageConstants.EN, ResponseCodes.SUCCESS_CODE, countryRepo.findByIsActive(true));
		} else {
			List<CountryConfigurationModel> result = countryRepo.findByIsActive(true).stream()
					.filter(i -> i.getCountryCodeA2().equalsIgnoreCase(filter)).collect(Collectors.toList());
			return response.buildResponse(LanguageConstants.EN, ResponseCodes.SUCCESS_CODE, result);
		}
	}

}
