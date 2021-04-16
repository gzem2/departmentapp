package com.gzem2.departmentapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.time.LocalDate;

import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.model.Employee;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeDaoTests {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Autowired
	DepartmentDao depdao;

	@Autowired
	EmployeeDao dao;
	
	Employee emp;
	Long new_emp_id;

	@BeforeAll
	void beforeAllTests() {
		Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
		flyway.migrate();
	}

	@Test
	@Order(1)
	void testCreate() {
		depdao.deleteDepartment(99L);
		depdao.createDepartment(new Department(99L, "Test Department"));

		emp = new Employee(99L, "surname", "name", "patronymic", LocalDate.parse("1970-11-10"), 10000);
		new_emp_id = dao.createEmployee(emp);
		emp.setId(new_emp_id);
		assertNotNull(new_emp_id);
	}

	@Test
	@Order(2)
	void testFindEmployeeById() {
		Employee found = dao.findEmployeeById(new_emp_id);
		assertEquals(new_emp_id, found.getId());
	}

	@Test
	@Order(3)
	void testFindEmployeesByDepartmentId() {
		List<Employee> lemp = dao.findEmployeesByDepartmentId(99L);
		assertTrue(lemp.get(0) instanceof Employee);

	}

	@Test
	@Order(4)
	void testFindEmployeesBySurname() {
		List<Employee> lemp = dao.findEmployeesBySurname("surname");
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(5)
	void testFindEmployeesByName() {
		List<Employee> lemp = dao.findEmployeesByName("name");
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(6)
	void testFindEmployeesByPatronymic() {
		List<Employee> lemp = dao.findEmployeesByPatronymic("patronymic");
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(7)
	void testFindEmployeesByFullName() {
		List<Employee> lemp = dao.findEmployeesByFullName("surname name patronymic");
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(8)
	void testFindEmployeesByBirthday() {
		List<Employee> lemp = dao.findEmployeesByBirthday(LocalDate.parse("1970-11-10"));
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(9)
	void testFindEmployeesBySalary() {
		List<Employee> lemp = dao.findEmployeesBySalary(10000);
		assertTrue(lemp.get(0) instanceof Employee);
	}

	@Test
	@Order(10)
	void testFindAllEmployees() {
		List<Employee> found = dao.findAllEmployees();
		assertTrue(found instanceof List<?>);
		assertTrue(found.size() != 0);
	}

	@Test
	@Order(11)
	void testUpdateEmployee() {
		emp.setSurname("Different");
		dao.updateEmployee(new_emp_id, emp);

		Employee found = dao.findEmployeeById(new_emp_id);
		assertEquals("Different", found.getSurname());
	}

	@Test
	@Order(12)
	void testDeleteEmployee() {
		dao.deleteEmployee(new_emp_id);
		assertEquals(null, dao.findEmployeeById(new_emp_id));
	}
}
