package com.odin.ref_data.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.odin.ref_data.enums.CustomerType;
import com.odin.ref_data.service.FormService;
import com.odin.ref_data.service.impl.CustomerFormServiceImpl;

@Component
public class FormFactory {
	
	@Autowired
	private CustomerFormServiceImpl customerForm;
	
	public FormService getInstance(String profile) {
		switch (CustomerType.valueOf(profile)) {
		case CUSTOMER:
			return customerForm;
		default:
			return null;
		}
	}

}
