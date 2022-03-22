package com.kumuluzee.employee.service;

import static com.kumuluzee.employee.constant.AppConstants.DEFAULT_LIMIT;
import static com.kumuluzee.employee.constant.AppConstants.DEFAULT_OFFSET;
import static com.kumuluzee.employee.constant.AppConstants.MAX_LIMIT;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;

import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import com.kumuluzee.employee.mapper.EmployeeMapper;

public class SearchServiceImplConfig {

	@Bean
	public EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}
	
	@Bean
	public EmployeeMapper employeeMapper() {
		return new EmployeeMapper();
	}
	
	/*
	 * @Bean public QueryStringDefaults getQueryStringDefaults() { return new
	 * QueryStringDefaults(); }
	 */
	
}
