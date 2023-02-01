package com.excel.file.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("test")
public class Sheet1 {

	@Id
	private String id;

	private String EEID;

	private String full_name;

	private String job_title;
	
	private String department;
	
	private String business_unit;
	
	private String gender;
	
	private String ethnicity;
	
	private int  age;
	
	private String hire_date;
	
	private String annual_salary;
	
	private String bonus;
	
	private String country;
	
	private String city;
	
	private String exit_date;

}
