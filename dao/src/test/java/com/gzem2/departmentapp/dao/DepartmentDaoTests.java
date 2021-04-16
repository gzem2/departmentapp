package com.gzem2.departmentapp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.gzem2.departmentapp.model.Department;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentDaoTests {

	@Autowired
	DepartmentDao dao;
	
	Department dep;
	Long new_dep_id;

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
