package com.gzem2.departmentapp.service;

import java.time.LocalDate;
import java.util.List;

import com.gzem2.departmentapp.model.Employee;

public interface IEmployeeService {

    Integer findEmployeeAverageSalaryByDepartment(Long id);

    List<Employee> findEmployeesBornBetween(LocalDate dateFrom, LocalDate dateTo);

    public Long createEmployee(Employee emp);

    public Employee findEmployeeById(Long id);

    public List<Employee> findEmployeesByDepartmentId(Long id);

    public List<Employee> findEmployeesBySurname(String surname);

    public List<Employee> findEmployeesByName(String name);

    public List<Employee> findEmployeesByPatronymic(String patronymic);

    public List<Employee> findEmployeesByFullName(String fullname);

    public List<Employee> findEmployeesByBirthday(LocalDate birthday);

    public List<Employee> findEmployeesBySalary(Integer salary);

    public List<Employee> findAllEmployees();
    
    public void updateEmployee(Long id, Employee emp);

    public void deleteEmployee(Long id);

}
