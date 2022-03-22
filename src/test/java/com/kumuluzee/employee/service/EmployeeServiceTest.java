package com.kumuluzee.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import com.kumuluzee.employee.dto.EmployeeDto;
import com.kumuluzee.employee.dto.ListEmployeeDto;
import com.kumuluzee.employee.mapper.EmployeeMapper;
import com.kumuluzee.employee.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SearchServiceImplConfig.class, loader = AnnotationConfigContextLoader.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@MockBean
	private QueryStringDefaults queryStringDefaults;
	
	@MockBean
	private EntityManager entityManager;
	
	private List<Employee> employeeList;
	@Before
	public void setUp() {
		Employee employee = Employee.builder()
				.firstName("First")
				.lastName("lastName")
				.email("test@email.com")
				.id(10L)
				.build();
		
		employeeList = Arrays.asList(employee);
		
	}
	
	@Test
	public void testSearchService() {
		String filter = "filter=firstName:EQ:Sudeep"; 
		QueryParameters parameters = QueryParameters.queryEncoded(filter)
				.enableOrder(true)
				.build();
		when(JPAUtils.queryEntities(entityManager, Employee.class, parameters)).thenReturn(employeeList);
		when(JPAUtils.queryEntitiesCount(entityManager, Employee.class, parameters)).thenReturn(1L);
		
		ListEmployeeDto listEmployeeDto = employeeService.search(filter);
		assertEquals(1, listEmployeeDto.getCount());
	}
}
