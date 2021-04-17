package com.gzem2.departmentapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import com.gzem2.departmentapp.dao.IDepartmentDao;
import com.gzem2.departmentapp.model.Department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DepartmentServiceTests {

	@Mock
	private IDepartmentDao depdao;

	@InjectMocks
	DepartmentService depserv;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		List<Department> deps = Arrays.asList(new Department("Department A"), new Department("Department B"),
				new Department("Department C"));
		Mockito.when(depdao.findDepartmentById(1L)).thenReturn(new Department(1L, "Example"));
		Mockito.when(depdao.findDepartmentsByName("Department A")).thenReturn(Arrays.asList(deps.get(0)));
		Mockito.when(depdao.findAllDepartments()).thenReturn(deps);
	}

	@Test
	public void testCreate() {
		Long result = depserv.createDepartment(new Department("Test"));
		assertNotNull(result);
	}

	@Test
	public void testFindDepartmentById() {
		Department dep = depserv.findDepartmentById(1L);
		assertNotNull(dep);
	}

	@Test
	public void testFindDepartmentsByName() {
		List<Department> deps = depserv.findDepartmentsByName("Department A");
		assertNotNull(deps);
	}

	@Test
	public void testFindAllDepartments() {
		List<Department> deps = depserv.findAllDepartments();
		assertNotNull(deps);
	}

	@Test
    public void testUpdateDepartment() {
		Mockito.when(depdao.findDepartmentById(1L)).thenReturn(new Department("Different"));
		depserv.updateDepartment(1L, new Department("Different"));
		Department dep = depserv.findDepartmentById(1L);
		assertEquals(dep.getDepartmentName(), "Different");
	}

	@Test
	public void testDeleteDepartment() {
		Mockito.when(depdao.findDepartmentById(1L)).thenReturn(null);
		depserv.deleteDepartment(1L);
		assertEquals(null, depdao.findDepartmentById(1L));
	}

}
