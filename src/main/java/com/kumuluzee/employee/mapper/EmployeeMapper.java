package com.kumuluzee.employee.mapper;

import org.springframework.beans.BeanUtils;

import com.kumuluzee.employee.dto.EmployeeDto;
import com.kumuluzee.employee.model.Employee;

public class EmployeeMapper {

	public static EmployeeDto toDTO(Employee e) {
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(e, dto);
		return dto;
	}
}
