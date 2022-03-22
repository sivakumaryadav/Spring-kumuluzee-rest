package com.kumuluzee.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import com.kumuluzee.employee.dto.ListEmployeeDto;
import com.kumuluzee.employee.mapper.EmployeeMapper;
import com.kumuluzee.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private QueryStringDefaults queryStringDefaults;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public ListEmployeeDto search(String queryString) {
		QueryParameters parameters = QueryParameters.queryEncoded(queryString)
				.enableOrder(true)
				.build();
		// Query Validation
		
		List<Employee> employees = JPAUtils.queryEntities(entityManager, Employee.class, parameters);
		Long employeesCount = JPAUtils.queryEntitiesCount(entityManager, Employee.class, parameters);
		
		ListEmployeeDto employeeDto = new ListEmployeeDto();
		employeeDto.setCount(employeesCount);
		employeeDto.setEmployeeDtos(employees.stream().map(e -> EmployeeMapper.toDTO(e)).collect(Collectors.toList()));
		return employeeDto;
	}

	@Override
	public ListEmployeeDto searchQueryString(String queryString) {
		QueryParameters parameters = queryStringDefaults.builder()
				.queryEncoded(queryString)
				.enablePagination(true)
				.enableOrder(true)
				.build();
		// Query Validation
		
		List<Employee> employees = JPAUtils.queryEntities(entityManager, Employee.class, parameters);
		Long employeesCount = JPAUtils.queryEntitiesCount(entityManager, Employee.class, parameters);
		
		ListEmployeeDto employeeDto = new ListEmployeeDto();
		employeeDto.setCount(employeesCount);
		employeeDto.setEmployeeDtos(employees.stream().map(e -> EmployeeMapper.toDTO(e)).collect(Collectors.toList()));
		return employeeDto;
	}

}
