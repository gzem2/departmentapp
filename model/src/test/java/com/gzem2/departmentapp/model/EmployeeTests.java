package com.gzem2.departmentapp.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class EmployeeTests {

	LocalDate birthday = LocalDate.parse("1970-01-01");
	Employee e = new Employee(1L, 3L, "Surname", "Name", "Patronymic", birthday, 10000);

	@Test
	void testGetters() {
		assertEquals(e.getId(), 1L);
		assertEquals(e.getDepartmentId(), 3L);
		assertEquals(e.getSurname(), "Surname");
		assertEquals(e.getName(), "Name");
		assertEquals(e.getPatronymic(), "Patronymic");;
		assertEquals(e.getBirthday(), birthday);
		assertEquals(e.getSalary(), 10000);
	}

	@Test
	void testSetters() {
		LocalDate newbirthday = LocalDate.parse("1970-11-10");

		e.setId(2L);
		e.setDepartmentId(4L);
		e.setSurname("Surname2");
		e.setName("Name2");
		e.setPatronymic("Patronymic2");
		e.setBirthday(newbirthday);
		e.setSalary(20000);

		assertEquals(e.getId(), 2L);
		assertEquals(e.getDepartmentId(), 4L);
		assertEquals(e.getSurname(), "Surname2");
		assertEquals(e.getName(), "Name2");
		assertEquals(e.getPatronymic(), "Patronymic2");
		assertEquals(e.getBirthday(), newbirthday);
		assertEquals(e.getSalary(), 20000);	}
}
