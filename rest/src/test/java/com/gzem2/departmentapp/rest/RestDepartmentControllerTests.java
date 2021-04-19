package com.gzem2.departmentapp.rest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.service.IDepartmentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RestDepartmentController.class)
public class RestDepartmentControllerTests {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartmentService service;

    @Test
    public void testGetAllDepartments() throws Exception {
        when(service.findAllDepartments()).thenReturn(Arrays.asList(new Department(1L, "Department A")));
        this.mockMvc.perform(get("/departments")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Department A")));
    }

    @Test
    public void testGetById() throws Exception {
        when(service.findDepartmentById(1L)).thenReturn(new Department(1L, "Department A"));
        this.mockMvc.perform(get("/departments/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Department A")));
    }

    @Test
    public void testGetByName() throws Exception {
        when(service.findDepartmentsByName("Department A"))
                .thenReturn(Arrays.asList(new Department(1L, "Department A")));
        this.mockMvc.perform(get("/departments/name/Department%20A")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Department A")));
    }

    @Test
    public void testCreate() throws Exception {
        when(service.createDepartment(any(Department.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/departments").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new Department(1L, "Department A"))))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1")));
    }

    @Test
    public void testUpdate() throws Exception {
        doNothing().when(service).updateDepartment(any(Long.class), any(Department.class));
        this.mockMvc
                .perform(
                        put("/departments/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(new Department(1L, "Department B"))))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(service).deleteDepartment(any(Long.class));
        this.mockMvc.perform(delete("/departments/1")).andDo(print()).andExpect(status().isOk());
    }
}
