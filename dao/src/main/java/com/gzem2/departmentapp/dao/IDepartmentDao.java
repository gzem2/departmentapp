package com.gzem2.departmentapp.dao;

import java.util.List;

import com.gzem2.departmentapp.model.Department;

public interface IDepartmentDao {

    public Long createDepartment(Department dep);

    public Department findDepartmentById(Long id);

    public List<Department> findDepartmentsByName(String departmentName);

    public List<Department> findAllDepartments();
    
    public void updateDepartment(Long id, Department dep);

    public void deleteDepartment(Long id);
    
}
