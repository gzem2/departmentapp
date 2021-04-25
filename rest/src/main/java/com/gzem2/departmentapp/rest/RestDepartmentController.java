package com.gzem2.departmentapp.rest;

import com.gzem2.departmentapp.model.Department;
import com.gzem2.departmentapp.service.IDepartmentService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
class RestDepartmentController {

    @Autowired
    private IDepartmentService service;

    @GetMapping("/departments")
    public List<Department> getAll() {
        List<Department> deps = service.findAllDepartments();
        if(deps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return deps;
        }
    }

    @GetMapping("/departments/{id}")
    public Department getById(@PathVariable Long id) {
        Department dep = service.findDepartmentById(id);
        if(dep == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return dep;
        }
    }

    @GetMapping("/departments/name/{departmentName}")
    public List<Department> getByName(@PathVariable String departmentName) {
        try {
            departmentName = URLDecoder.decode(departmentName, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Department> deps = service.findDepartmentsByName(departmentName);
        if(deps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return deps;
        }
    }

    @PostMapping("/departments")
    public Long create(@RequestBody Department dep) {
        Long id = service.createDepartment(dep);
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return id;
        }
    }

    @PutMapping("/departments/{id}")
    public void update(@RequestBody Department dep, @PathVariable Long id) {
        try {
            service.updateDepartment(id, dep);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/departments/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.deleteDepartment(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "department is not empty");
        }
    }
}