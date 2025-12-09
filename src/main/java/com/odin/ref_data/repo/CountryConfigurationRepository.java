package com.odin.ref_data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odin.ref_data.entity.CountryConfigurationModel;

public interface CountryConfigurationRepository extends JpaRepository<CountryConfigurationModel, Integer>{

	List<CountryConfigurationModel> findByIsActive(boolean b);

}
