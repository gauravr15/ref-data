package com.odin.ref_data.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "country_configuration")
@Getter
@Setter
public class CountryConfigurationModel {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "country_code_a2")
	private String countryCodeA2;
	
	@Column(name = "country_code_a3")
	private String countryCodeA3;
	
	@Column(name = "mobile_prefix")
	private String mobilePrefix;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Timestamp createdTimestamp;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	private Timestamp updatedTimestamp;

}
