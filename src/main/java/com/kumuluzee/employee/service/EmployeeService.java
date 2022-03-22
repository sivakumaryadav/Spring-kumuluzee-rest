package com.kumuluzee.employee.service;

import com.kumuluzee.employee.dto.ListEmployeeDto;

public interface EmployeeService {

	public ListEmployeeDto search(final String queryString);

	ListEmployeeDto searchQueryString(final String queryString);
	
}
