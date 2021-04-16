package com.gzem2.departmentapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DepartmentTests {

	Department d = new Department(1L, "Test");

	@Test
	void testGetters() {
		assertEquals(d.getId(), 1L);
		assertEquals(d.getDepartmentName(), "Test");
	}

	@Test
	void testSetters() {
		d.setId(2L);
		d.setDepartmentName("Example");

		assertEquals(d.getId(), 2L);
		assertEquals(d.getDepartmentName(), "Example");
	}
}
