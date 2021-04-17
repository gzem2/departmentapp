package com.gzem2.departmentapp.service;

import java.util.List;

import com.gzem2.departmentapp.model.Department;

public interface IDepartmentService {

    public Long createDepartment(Department dep);

    public Department findDepartmentById(Long id);

    public List<Department> findDepartmentsByName(String departmentName);

    public List<Department> findAllDepartments();
    
    public void updateDepartment(Long id, Department dep);

    public void deleteDepartment(Long id);
    
}
