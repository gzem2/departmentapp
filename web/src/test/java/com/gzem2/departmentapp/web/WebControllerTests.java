package com.gzem2.departmentapp.web;

import com.gzem2.departmentapp.model.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.http.HttpRequest;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(WebController.class)
class WebControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private RestTemplate restTemplate;

        @BeforeEach
        public void setUp() throws Exception {
                String apiBase = "http://localhost:8080/rest";

                List<Department> depsL = Arrays.asList(new Department(1L, "Отдел A"), new Department(2L, "Отдел B"),
                                new Department(3L, "Отдел C"));
                ResponseEntity<List<Department>> depsLMock = (ResponseEntity<List<Department>>) mock(
                                ResponseEntity.class);
                when(depsLMock.getBody()).thenReturn(depsL);

                when(restTemplate.exchange(eq(apiBase + "/departments"), eq(HttpMethod.GET), eq(null),
                                eq(new ParameterizedTypeReference<List<Department>>() {
                                }))).thenReturn(depsLMock);

                when(restTemplate.getForObject(apiBase + "/employees/average/1", String.class)).thenReturn("25833");
                when(restTemplate.getForObject(apiBase + "/employees/average/2", String.class)).thenReturn("23000");
                when(restTemplate.getForObject(apiBase + "/employees/average/3", String.class)).thenReturn("34000");

                List<Employee> empsL = Arrays.asList(
                                new Employee(1L, 1L, "Андреев", "Артем", "Антонович", LocalDate.parse("1982-02-26"),
                                                36000),
                                new Employee(2L, 1L, "Виноградов", "Валерий", "Андреевич",
                                                LocalDate.parse("1991-11-24"), 27000),
                                new Employee(3L, 1L, "Маслов", "Анатолий", "Иосифович", LocalDate.parse("1997-05-29"),
                                                28000));
                ResponseEntity<List<Employee>> empsLMock = (ResponseEntity<List<Employee>>) mock(ResponseEntity.class);
                when(empsLMock.getBody()).thenReturn(empsL);

                when(restTemplate.exchange(eq(apiBase + "/employees"), eq(HttpMethod.GET), eq(null),
                                eq(new ParameterizedTypeReference<List<Employee>>() {
                                }))).thenReturn(empsLMock);
                when(restTemplate.exchange(eq(apiBase + "/employees/department/1"), eq(HttpMethod.GET), eq(null),
                                eq(new ParameterizedTypeReference<List<Employee>>() {
                                }))).thenReturn(empsLMock);

                ResponseEntity<List<Employee>> bdayMock = (ResponseEntity<List<Employee>>) mock(ResponseEntity.class);
                when(bdayMock.getBody()).thenReturn(Arrays.asList(empsL.get(0)));
                when(restTemplate.exchange(eq(apiBase + "/employees/birthday/1982-02-26"), eq(HttpMethod.GET), eq(null),
                                eq(new ParameterizedTypeReference<List<Employee>>() {
                                }))).thenReturn(bdayMock);

                ResponseEntity<List<Employee>> betweenMock = (ResponseEntity<List<Employee>>) mock(
                                ResponseEntity.class);
                when(betweenMock.getBody()).thenReturn(Arrays.asList(empsL.get(1)));
                when(restTemplate.exchange(eq(apiBase + "/employees/bornbetween/1982-02-26/1997-05-29"),
                                eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<List<Employee>>() {
                                }))).thenReturn(betweenMock);

                when(restTemplate.getForObject(apiBase + "/departments/1", Department.class)).thenReturn(depsL.get(0));
                when(restTemplate.getForObject(apiBase + "/departments/2", Department.class)).thenReturn(depsL.get(1));
                when(restTemplate.getForObject(apiBase + "/departments/3", Department.class)).thenReturn(depsL.get(2));
                when(restTemplate.getForObject(apiBase + "/employees/1", Employee.class)).thenReturn(empsL.get(0));
                when(restTemplate.getForObject(apiBase + "/employees/2", Employee.class)).thenReturn(empsL.get(1));
                when(restTemplate.getForObject(apiBase + "/employees/3", Employee.class)).thenReturn(empsL.get(2));

                ResponseEntity<String> rs = (ResponseEntity<String>) mock(ResponseEntity.class);
                when(restTemplate.postForEntity(eq(apiBase + "/employees"), Mockito.any(HttpRequest.class),
                                eq(String.class))).thenReturn(rs);
                doNothing().doThrow(new RuntimeException()).when(restTemplate).put(eq(apiBase + "/employees/1"),
                                Mockito.any(HttpRequest.class), eq(String.class));
                doNothing().doThrow(new RuntimeException()).when(restTemplate).delete(eq(apiBase + "/employees/1"));
                when(restTemplate.postForEntity(eq(apiBase + "/departments"), Mockito.any(HttpRequest.class),
                                eq(String.class))).thenReturn(rs);
                doNothing().doThrow(new RuntimeException()).when(restTemplate).put(eq(apiBase + "/departments/1"),
                                Mockito.any(HttpRequest.class), eq(String.class));
                doNothing().doThrow(new RuntimeException()).when(restTemplate).delete(eq(apiBase + "/departments/1"));
        }

        @Test
        void testGetIndex() throws Exception {
                this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("salaries", hasSize(3))).andExpect(model().attribute(
                                                "data", hasItem(hasProperty("departmentName", is("Отдел A")))));
        }

        @Test
        void testGetDepartmentView() throws Exception {
                this.mockMvc.perform(get("/department/1")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("title", is("Отдел A")))
                                .andExpect(model().attribute("departmentId", is("1")))
                                .andExpect(model().attribute("data", hasItem(hasProperty("surname", is("Андреев")))));
        }

        @Test
        void testGetEmployeeById() throws Exception {
                this.mockMvc.perform(get("/employee/1")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("data", hasProperty("surname", is("Андреев"))));
        }

        @Test
        void testGetEmployeesList() throws Exception {
                this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("title", is("все отделы")))
                                .andExpect(model().attribute("data", hasItem(hasProperty("surname", is("Виноградов")))))
                                .andExpect(model().attribute("data", hasSize(3)));
        }

        @Test
        void testFindBirthday() throws Exception {
                this.mockMvc.perform(get("/employees?birthday=1982-02-26")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("data", hasItem(hasProperty("surname", is("Андреев")))));
        }

        @Test
        void testFindBornBetween() throws Exception {
                this.mockMvc.perform(get("/employees?birthdayFrom=1982-02-26&birthdayTo=1997-05-29")).andDo(print())
                                .andExpect(status().isOk()).andExpect(model().attribute("data",
                                                hasItem(hasProperty("surname", is("Виноградов")))));
        }

        @Test
        void testGetEmployeeEdit() throws Exception {
                this.mockMvc.perform(get("/employee/edit")).andDo(print()).andExpect(status().isOk()).andExpect(model()
                                .attribute("departmentList", hasItem(hasProperty("departmentName", is("Отдел A")))));
                this.mockMvc.perform(get("/employee/edit?department=3")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("departmentId", is(3L)));
                this.mockMvc.perform(get("/employee/edit?id=3")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("data", hasProperty("surname", is("Маслов"))));
        }

        @Test
        void testPostEmployee() throws Exception {
                this.mockMvc.perform(post("/employee").content(
                                "id%3D1%26departmentId%3D1%26surname%3Dsurname%26name%3Dname%26patronymic%3Dpatronymic%26birthday%3D1970-01-01%26salary%3D10000")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andDo(print())
                                .andExpect(status().is3xxRedirection());
        }

        @Test
        void testGetDepartmentEdit() throws Exception {
                this.mockMvc.perform(get("/department/edit")).andDo(print()).andExpect(status().isOk());
                this.mockMvc.perform(get("/department/edit?id=3")).andDo(print()).andExpect(status().isOk())
                                .andExpect(model().attribute("data", hasProperty("departmentName", is("Отдел C"))));
        }

        @Test
        void testDeleteEmployee() throws Exception {
                this.mockMvc.perform(get("/employee/delete/1")).andDo(print()).andExpect(status().is3xxRedirection());
        }

        @Test
        void testPostDepartment() throws Exception {
                this.mockMvc.perform(post("/department").content(
                                "id%3D1%26departmentName%3D%D0%9E%D1%82%D0%B4%D0%B5%D0%BB+A")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)).andDo(print())
                                .andExpect(status().is3xxRedirection());
        }

        @Test
        void testDeleteDepartment() throws Exception {
                this.mockMvc.perform(get("/department/delete/1")).andDo(print()).andExpect(status().is3xxRedirection());
        }
}