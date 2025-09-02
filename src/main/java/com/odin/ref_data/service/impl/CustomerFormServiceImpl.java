package com.odin.ref_data.service.impl;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.odin.ref_data.constants.LanguageConstants;
import com.odin.ref_data.constants.ResponseCodes;
import com.odin.ref_data.dto.FormMetadataResponseDTO;
import com.odin.ref_data.dto.FormRequestDTO;
import com.odin.ref_data.dto.ResponseDTO;
import com.odin.ref_data.enums.CustomerType;
import com.odin.ref_data.repo.FormRepository;
import com.odin.ref_data.service.FormService;
import com.odin.ref_data.utility.ResponseObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerFormServiceImpl implements FormService {

	@Autowired
	private ResponseObject response;

	@Autowired
	private FormRepository formRepo;

	@Override
	public ResponseDTO getForm(HttpServletRequest req, FormRequestDTO formDTO) {
		try {
		formDTO.setUserType(CustomerType.CUSTOMER.name().toLowerCase());
		log.info("Fetching form data for customer : {}", formDTO);
		List<FormMetadataResponseDTO> data = formRepo.findByModuleAndSubModuleAndUserType(formDTO.getModule(),
				formDTO.getSubModule(), formDTO.getUserType());
		if (ObjectUtils.isEmpty(data)) {
			return response.buildResponse(LanguageConstants.EN, ResponseCodes.FAILURE_CODE);
		}
		data.sort(Comparator.comparing(FormMetadataResponseDTO::getSequence));
		return response.buildResponse(LanguageConstants.EN, ResponseCodes.SUCCESS_CODE, data);
		}
		catch(Exception e) {
			log.error("Error while fetching form metadata : {}", ExceptionUtils.getStackFrames(e));
			return response.buildResponse(LanguageConstants.EN, ResponseCodes.EXCEPTION_CODE);
		}
	}

}
