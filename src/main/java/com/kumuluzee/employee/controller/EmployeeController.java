package com.kumuluzee.employee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumuluzee.employee.dto.ListEmployeeDto;
import com.kumuluzee.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(path = "/search/v1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListEmployeeDto search(HttpServletRequest servletRequest) {
		String searchQuery = servletRequest.getQueryString();
		log.info("Employee Query Search filter : " + searchQuery);
		ListEmployeeDto employeeDto = employeeService.search(searchQuery);
		log.info("Employees count : " + employeeDto.getCount());
		return employeeDto;
	}
	
	@GetMapping(path = "/search/v1/qsp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ListEmployeeDto searchQsp(HttpServletRequest servletRequest) {
		String searchQuery = servletRequest.getQueryString();
		log.info("Employee Query Search filter : " + searchQuery);
		ListEmployeeDto employeeDto = employeeService.searchQueryString(searchQuery);
		log.info("Employees count : " + employeeDto.getCount());
		return employeeDto;
	}
}
