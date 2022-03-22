package com.kumuluzee.employee.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListEmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long count;
	private List<EmployeeDto> employeeDtos;
}
