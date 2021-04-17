package com.gzem2.departmentapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;

import com.gzem2.departmentapp.dao.IEmployeeDao;
import com.gzem2.departmentapp.dao.IDepartmentDao;
import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.model.Employee;

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
class EmployeeServiceTests {

	@Mock
	private IEmployeeDao empdao;

    @Mock
    private IDepartmentDao depdao;

	@InjectMocks
	EmployeeService empserv;

    @InjectMocks
    DepartmentService depserv;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);

		List<Department> deps = Arrays.asList(new Department("Department A"), new Department("Department B"),
				new Department("Department C"));
        List<Employee> emps = Arrays.asList(
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1970, 1, 1), 10000),
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1971, 1, 1), 15000),
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1972, 1, 1), 20000),
            new Employee(2L, "surname", "name", "patronymic", LocalDate.of(1973, 1, 1), 25000)
        );

		Mockito.when(depdao.findDepartmentById(1L)).thenReturn(deps.get(0));
		Mockito.when(depdao.findDepartmentsByName("Department A")).thenReturn(Arrays.asList(deps.get(0)));
		Mockito.when(depdao.findAllDepartments()).thenReturn(deps);

        Mockito.when(empdao.findEmployeeById(1L)).thenReturn(emps.get(0));
        Mockito.when(empdao.findEmployeesByDepartmentId(1L)).thenReturn(Arrays.asList(emps.get(0), emps.get(1), emps.get(2)));
        Mockito.when(empdao.findEmployeesByBirthday(LocalDate.of(1970, 1, 1))).thenReturn(Arrays.asList(emps.get(0)));
        Mockito.when(empdao.findEmployeesBySurname("surname")).thenReturn(emps);
        Mockito.when(empdao.findEmployeesByName("name")).thenReturn(emps);
        Mockito.when(empdao.findEmployeesByPatronymic("patronymic")).thenReturn(emps);
        Mockito.when(empdao.findEmployeesByFullName("surname name patronymic")).thenReturn(emps);
        Mockito.when(empdao.findEmployeesBySalary(1000)).thenReturn(Arrays.asList(emps.get(0)));
        Mockito.when(empdao.findAllEmployees()).thenReturn(emps);
	}

    @Test
    public void testFindEmployeeAverageSalaryByDepartment() {
        Integer avg = empserv.findEmployeeAverageSalaryByDepartment(1L);
        assertEquals(15000, avg);
    }

    @Test
    public void testFindEmployeesBornBetween() {
        List<Employee> bb = empserv.findEmployeesBornBetween(LocalDate.of(1970, 2, 1), LocalDate.of(1972, 2, 1));
        assertEquals(2, bb.size());
    }

	@Test
	public void testCreate() {
		Employee emp = new Employee(1L, "surname", "name", "patronymic", LocalDate.parse("1970-11-10"), 10000);
		Long id = empdao.createEmployee(emp);
		assertNotNull(id);
	}

	@Test
	public void testFindEmployeeById() {
		Employee emp = empserv.findEmployeeById(1L);
		assertNotNull(emp);
	}

	@Test
	public void testFindEmployeesByDepartmentId() {
		List<Employee> el = empserv.findEmployeesByDepartmentId(1L);
		assertNotNull(el);
	}

    @Test
	public void testFindEmployeesBySurname() {
		List<Employee> el = empserv.findEmployeesBySurname("surname");
		assertNotNull(el);
	}

	@Test
	void testFindEmployeesByName() {
		List<Employee> el = empserv.findEmployeesByName("name");
		assertNotNull(el);
	}

	@Test
	void testFindEmployeesByPatronymic() {
		List<Employee> el = empserv.findEmployeesByPatronymic("patronymic");
		assertNotNull(el);
	}

	@Test
	void testFindEmployeesByFullName() {
		List<Employee> el = empserv.findEmployeesByFullName("surname name patronymic");
		assertNotNull(el);
	}

	@Test
	void testFindEmployeesByBirthday() {
		List<Employee> el = empserv.findEmployeesByBirthday(LocalDate.of(1970, 1, 1));
		assertNotNull(el);
	}

	@Test
	void testFindEmployeesBySalary() {
		List<Employee> el = empserv.findEmployeesBySalary(10000);
		assertNotNull(el);
	}


	@Test
	public void testFindAllEmployees() {
		List<Employee> el = empserv.findAllEmployees();
		assertNotNull(el);
	}

	@Test
    public void testUpdateEmployee() {
		Mockito.when(empdao.findEmployeeById(1L)).thenReturn(new Employee(1L, "different", "name", "patronymic", LocalDate.of(1970, 1, 1), 10000));
		empserv.updateEmployee(1L, new Employee(1L, "different", "name", "patronymic", LocalDate.of(1970, 1, 1), 10000));
		Employee emp = empserv.findEmployeeById(1L);
		assertEquals(emp.getSurname(), "different");
	}

	@Test
	public void testDeleteEmployee() {
		Mockito.when(empdao.findEmployeeById(1L)).thenReturn(null);
		empserv.deleteEmployee(1L);
		assertEquals(null, empdao.findEmployeeById(1L));
	}

}
