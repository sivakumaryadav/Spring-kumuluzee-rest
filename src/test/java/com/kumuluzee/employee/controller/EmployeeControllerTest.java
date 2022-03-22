package com.kumuluzee.employee.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluzee.employee.dto.EmployeeDto;
import com.kumuluzee.employee.dto.ListEmployeeDto;
import com.kumuluzee.employee.service.EmployeeService;

/**
 * @author sivak
 * 
 * JUnit 4
 *
 */
@RunWith(SpringRunner.class)
@EnableWebMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	@MockBean
	private EmployeeService employeeService;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private ListEmployeeDto listEmployeeDto;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		
		EmployeeDto employeeDto = EmployeeDto.builder()
				.firstName("First")
				.lastName("lastName")
				.email("test@email.com")
				.id(10L)
				.build();
		
		List<EmployeeDto> employeeDtos = Arrays.asList(employeeDto);
		
		listEmployeeDto = ListEmployeeDto.builder()
				.employeeDtos(employeeDtos)
				.count(employeeDtos.size())
				.build();
	}
	
	@Test
	public void testSearchService() throws Exception {
		String filter = "filter=firstName:EQ:Sudeep";
		when(employeeService.search(filter)).thenReturn(listEmployeeDto);

		mockMvc.perform(get("/search/v1?filter=firstName:EQ:Sudeep")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(employeeService, times(1)).search(filter);
		verifyNoMoreInteractions(employeeService);
	}

}
