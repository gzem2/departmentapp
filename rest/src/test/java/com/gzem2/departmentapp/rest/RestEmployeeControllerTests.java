package com.gzem2.departmentapp.rest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.model.Employee;
import com.gzem2.departmentapp.service.IEmployeeService;
import com.gzem2.departmentapp.service.IDepartmentService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestEmployeeController.class)
public class RestEmployeeControllerTests {
    
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmployeeService service;

    @MockBean
    private IDepartmentService depservice;

    private List<Department> deps;
    private List<Employee> emps;

    @BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);

		deps = Arrays.asList(new Department("Department A"), new Department("Department B"),
				new Department("Department C"));
        emps = Arrays.asList(
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1970, 1, 1), 10000),
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1971, 1, 1), 15000),
            new Employee(1L, "surname", "name", "patronymic", LocalDate.of(1972, 1, 1), 20000),
            new Employee(2L, "surname", "name", "patronymic", LocalDate.of(1973, 1, 1), 25000)
        );

		Mockito.when(depservice.findDepartmentById(1L)).thenReturn(deps.get(0));
		Mockito.when(depservice.findDepartmentsByName("Department A")).thenReturn(Arrays.asList(deps.get(0)));
		Mockito.when(depservice.findAllDepartments()).thenReturn(deps);

        Mockito.when(service.findEmployeeById(1L)).thenReturn(emps.get(0));
        Mockito.when(service.findEmployeesByDepartmentId(1L)).thenReturn(Arrays.asList(emps.get(0), emps.get(1), emps.get(2)));
        Mockito.when(service.findEmployeesByBirthday(LocalDate.of(1970, 1, 1))).thenReturn(Arrays.asList(emps.get(0)));
        Mockito.when(service.findEmployeesBySurname("surname")).thenReturn(emps);
        Mockito.when(service.findEmployeesByName("name")).thenReturn(emps);
        Mockito.when(service.findEmployeesByPatronymic("patronymic")).thenReturn(emps);
        Mockito.when(service.findEmployeesByFullName("surname name patronymic")).thenReturn(emps);
        Mockito.when(service.findEmployeesBySalary(1000)).thenReturn(Arrays.asList(emps.get(0)));
        Mockito.when(service.findAllEmployees()).thenReturn(emps);
	}

    @Test
    public void testGetAverageSalaryByDepartment() throws Exception {
        when(service.findEmployeeAverageSalaryByDepartment(1L)).thenReturn(15000);
        this.mockMvc.perform(get("/employees/average/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("15000")));
    }

    @Test
    public void testGetBornBetween() throws Exception {
        when(service.findEmployeesBornBetween(LocalDate.of(1970, 2, 1), LocalDate.of(1972, 2, 1))).thenReturn(Arrays.asList(emps.get(1), emps.get(2)));
        this.mockMvc.perform(get("/employees/bornbetween/1970-02-01/1972-02-01")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(service.findAllEmployees()).thenReturn(emps);
        this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testGetById() throws Exception {
        when(service.findEmployeeById(1L)).thenReturn(emps.get(0));
        this.mockMvc.perform(get("/employees/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testGetByDepartmentId() throws Exception {
        when(service.findEmployeesByDepartmentId(1L)).thenReturn(Arrays.asList(emps.get(0), emps.get(1), emps.get(2)));
        this.mockMvc.perform(get("/employees/department/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testGetBySurname() throws Exception {
        when(service.findEmployeesBySurname("surname"))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/surname/surname")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));
    }

    @Test
    public void testGetByName() throws Exception {
        when(service.findEmployeesByName("name"))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/name/name")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));
    }

    @Test
    public void testGetByPatronymic() throws Exception {
        when(service.findEmployeesByPatronymic("patronymic"))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/patronymic/patronymic")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("patronymic")));
    }

    @Test
    public void testGetByFullName() throws Exception {
        when(service.findEmployeesByFullName("surname name patronymic"))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/fullname/surname%20name%20patronymic")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testGetByBirthday() throws Exception {
        when(service.findEmployeesByBirthday(LocalDate.parse("1970-01-01")))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/birthday/1970-01-01")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }
    
    @Test
    public void testGetBySalary() throws Exception {
        when(service.findEmployeesBySalary(10000))
                .thenReturn(emps);
        this.mockMvc.perform(get("/employees/salary/10000")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("surname")));
    }

    @Test
    public void testCreate() throws Exception {
        when(service.createEmployee(any(Employee.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/employees").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(emps.get(0))))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1")));
    }

    @Test
    public void testUpdate() throws Exception {
        doNothing().when(service).updateEmployee(any(Long.class), any(Employee.class));
        this.mockMvc
                .perform(
                        put("/employees/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(emps.get(1))))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(service).deleteEmployee(any(Long.class));
        this.mockMvc.perform(delete("/employees/1")).andDo(print()).andExpect(status().isOk());
    }
}
