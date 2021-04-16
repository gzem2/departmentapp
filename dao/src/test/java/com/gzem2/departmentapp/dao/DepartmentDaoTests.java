package com.gzem2.departmentapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gzem2.departmentapp.model.Department;

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
class DepartmentDaoTests {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Autowired
	DepartmentDao dao;

	Department dep;
	Long new_dep_id;

	@BeforeAll
	void beforeAllTests() {
		Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
		flyway.migrate();
	}

	@Test
	@Order(1)
	void testCreate() {
		dep = new Department("Named Department");
		new_dep_id = dao.createDepartment(dep);
		dep.setId(new_dep_id);
		assertNotNull(new_dep_id);
	}

	@Test
	@Order(2)
	void testFindDepartmentById() {
		Department found = dao.findDepartmentById(new_dep_id);
		assertEquals(new_dep_id, found.getId());
	}

	@Test
	@Order(3)
	void testFindDepartmentsByName() {
		List<Department> found = dao.findDepartmentsByName("Named Department");
		assertEquals("Named Department", found.get(0).getDepartmentName());
	}

	@Test
	@Order(4)
	void testFindAllDepartments() {
		List<Department> found = dao.findAllDepartments();
		assertTrue(found instanceof List<?>);
		assertTrue(found.size() != 0);
	}

	@Test
	@Order(5)
	void testUpdateDepartment() {
		dep.setDepartmentName("Different");
		dao.updateDepartment(new_dep_id, dep);

		Department found = dao.findDepartmentById(new_dep_id);
		assertEquals("Different", found.getDepartmentName());
	}

	@Test
	@Order(6)
	void testDeleteDepartment() {
		dao.deleteDepartment(new_dep_id);
		assertEquals(null, dao.findDepartmentById(new_dep_id));
	}
}
