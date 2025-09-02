package com.odin.ref_data.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.odin.ref_data.dto.FormMetadataResponseDTO;
import com.odin.ref_data.dto.ResponseDTO;
import com.odin.ref_data.utility.SearchCriteria;
import com.odin.ref_data.utility.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FormRepository {
	
	@Value("${core.ref.url}")
	private String coreRefDataUrl;

	@Autowired
	private Utility utility;
	
	public static final String CUSTOMER = "/customer";
	public static final String FORM = "/form";

	public List<FormMetadataResponseDTO> findByModuleAndSubModuleAndUserType(String module, String subModule, String userType) {
		List<SearchCriteria> searchCriteriaList = new ArrayList<>();
	    searchCriteriaList.add(new SearchCriteria("module", ":", module,"AND"));
	    searchCriteriaList.add(new SearchCriteria("subModule", ":", subModule,"AND"));
	    searchCriteriaList.add(new SearchCriteria("userType", ":", userType,"AND"));
	    
	    // Make the REST call using your utility method
	    ResponseDTO response = utility.makeRestCall(
	    		coreRefDataUrl + CUSTOMER + FORM, 
	            searchCriteriaList, 
	            HttpMethod.POST, 
	            ResponseDTO.class
	    );
	    log.info("form response returned from core {}",response.toString());
	    return utility.getInstances(response, FormMetadataResponseDTO.class);
	}
	
	public FormMetadataResponseDTO findByModuleORSubModule(String module, String subModule) {
		List<SearchCriteria> searchCriteriaList = new ArrayList<>();
	    searchCriteriaList.add(new SearchCriteria("module", ":", module,"OR"));
	    searchCriteriaList.add(new SearchCriteria("subModule", ":", subModule,"OR"));
	    
	    // Make the REST call using your utility method
	    ResponseDTO response = utility.makeRestCall(
	    		coreRefDataUrl + CUSTOMER + FORM, 
	            searchCriteriaList, 
	            HttpMethod.POST, 
	            ResponseDTO.class
	    );
	    
	    return utility.getAnInstance(response.getData(), FormMetadataResponseDTO.class);
	}
	
}
