package com.odin.ref_data.service;

import javax.servlet.http.HttpServletRequest;

import com.odin.ref_data.dto.FormRequestDTO;
import com.odin.ref_data.dto.ResponseDTO;

public interface FormService {

	ResponseDTO getForm(HttpServletRequest req, FormRequestDTO formDTO);

}
