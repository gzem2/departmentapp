package com.gzem2.departmentapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gzem2.departmentapp.dao.IEmployeeDao;
import com.gzem2.departmentapp.model.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService implements IEmployeeService {

    private static Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private IEmployeeDao empdao;

    @Override
    public Integer findEmployeeAverageSalaryByDepartment(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeeAverageSalaryByDepartment(Long id) : {}", id);
        }

        Integer sum = 0;
        List<Employee> employees = this.findEmployeesByDepartmentId(id);

        for (Employee e : employees) {
            sum += e.getSalary();
        }

        Integer averageSalary = sum / employees.size();
        return averageSalary;
    }

    @Override
    public List<Employee> findEmployeesBornBetween(LocalDate dateFrom, LocalDate dateTo) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesBornBetween(LocalDate dateFrom, LocalDate dateTo) : {}, {}", dateFrom, dateTo);
        }

        List<Employee> employees = this.findAllEmployees();
        List<Employee> bornBetween = new ArrayList<Employee>();

        for (Employee e : employees) {
            LocalDate empDate = e.getBirthday();
            if (empDate.compareTo(dateFrom) > 0 && empDate.compareTo(dateTo) < 0) {
                bornBetween.add(e);
            }
        }

        return bornBetween;
    }

    @Override
    public Long createEmployee(Employee emp) {
        if(log.isInfoEnabled()) {
            log.info("call to createEmployee(Employee emp) : {}", emp);
        }

        return empdao.createEmployee(emp);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeeById(Long id) : {}", id);
        }

        return empdao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesByDepartmentId(Long id) : {}", id);
        }

        return empdao.findEmployeesByDepartmentId(id);
    }

    @Override
    public List<Employee> findEmployeesBySurname(String surname) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesBySurname(String surname) : {}", surname);
        }

        return empdao.findEmployeesBySurname(surname);
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesByName(String name) : {}", name);
        }
        
        return empdao.findEmployeesByName(name);
    }

    @Override
    public List<Employee> findEmployeesByPatronymic(String patronymic) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesByPatronymic(String patronymic) : {}", patronymic);
        }

        return empdao.findEmployeesByPatronymic(patronymic);
    }

    @Override
    public List<Employee> findEmployeesByFullName(String fullname) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesByFullName(String fullname) : {}", fullname);
        }

        return empdao.findEmployeesByFullName(fullname);
    }

    @Override
    public List<Employee> findEmployeesByBirthday(LocalDate birthday) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesByBirthday(LocalDate birthday) : {}", birthday);
        }

        return empdao.findEmployeesByBirthday(birthday);
    }

    @Override
    public List<Employee> findEmployeesBySalary(Integer salary) {
        if(log.isInfoEnabled()) {
            log.info("call to findEmployeesBySalary(Integer salary) : {}", salary);
        }

        return empdao.findEmployeesBySalary(salary);
    }

    @Override
    public List<Employee> findAllEmployees() {
        if(log.isInfoEnabled()) {
            log.info("call to findAllEmployees()");
        }

        return empdao.findAllEmployees();
    }

    @Override
    public void updateEmployee(Long id, Employee emp) {
        if(log.isInfoEnabled()) {
            log.info("call to updateEmployee(Long id, Employee emp) : {}, {}", id, emp);
        }

        empdao.updateEmployee(id, emp);
    }

    @Override
    public void deleteEmployee(Long id) {
        if(log.isInfoEnabled()) {
            log.info("call to deleteEmployee(Long id) : {}", id);
        }

        empdao.deleteEmployee(id);
    }
    
}
