package com.gzem2.departmentapp.rest;

import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.service.IDepartmentService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RestDepartmentController {

    @Autowired
    private IDepartmentService service;

    @GetMapping("/departments")
    public List<Department> getAll() {
        return service.findAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getById(@PathVariable Long id) {
        return service.findDepartmentById(id);
    }

    @GetMapping("/departments/name/{departmentName}")
    public List<Department> getByName(@PathVariable String departmentName) {
        try {
            departmentName = URLDecoder.decode(departmentName, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findDepartmentsByName(departmentName);
    }

    @PostMapping("/departments")
    public Long create(@RequestBody Department dep) {
        return service.createDepartment(dep);
    }

    @PutMapping("/departments/{id}")
    public void update(@RequestBody Department dep, @PathVariable Long id) {
        service.updateDepartment(id, dep);
    }

    @DeleteMapping("/departments/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDepartment(id);
    }
}