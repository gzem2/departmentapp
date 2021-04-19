package com.gzem2.departmentapp.service;

import java.util.List;

import com.gzem2.departmentapp.dao.IDepartmentDao;
import com.gzem2.departmentapp.model.Department;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentService implements IDepartmentService {

    private static Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private IDepartmentDao depdao;

    @Override
    public Long createDepartment(Department dep) {
        if(log.isInfoEnabled()) {
            log.info("call to createDepartment(Department dep) : {}", dep);
        }

        return depdao.createDepartment(dep);
    }

    @Override
    public Department findDepartmentById(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to findDepartmentById(Long id) : {}", id);
        }

        return depdao.findDepartmentById(id);
    }

    @Override
    public List<Department> findDepartmentsByName(String departmentName) {
        if(log.isInfoEnabled()) {
            log.info("call to findDepartmentsByName(String departmentName) : {}", departmentName);
        }

        return depdao.findDepartmentsByName(departmentName);
    }

    @Override
    public List<Department> findAllDepartments() {
        if(log.isInfoEnabled()) {
            log.info("call to findAllDepartments()");
        }

        return depdao.findAllDepartments();
    }

    @Override
    public void updateDepartment(Long id, Department dep) {
        if(log.isInfoEnabled()) {
            log.info("call to updateDepartment(Long id, Department dep) : {}, {}", id, dep);
        }

        depdao.updateDepartment(id, dep);
    }

    @Override
    public void deleteDepartment(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to deleteDepartment(Long id) : {}", id);
        }

        depdao.deleteDepartment(id);
    }

}