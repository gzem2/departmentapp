package com.gzem2.departmentapp.rest;

import com.gzem2.departmentapp.model.Employee;
import com.gzem2.departmentapp.service.IEmployeeService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
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
class RestEmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping("/employees/average/{id}")
    public Integer getAverageSalaryByDepartment(@PathVariable Long id) {
        return service.findEmployeeAverageSalaryByDepartment(id);
    }

    @GetMapping("/employees/bornbetween/{dateFrom}/{dateTo}")
    public List<Employee> getBornBetween(@PathVariable String dateFrom, @PathVariable String dateTo) {
        return service.findEmployeesBornBetween(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return service.findAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.findEmployeeById(id);
    }

    @GetMapping("/employees/department/{id}")
    public List<Employee> getByDepartmentId(@PathVariable Long id) {
        return service.findEmployeesByDepartmentId(id);
    }

    @GetMapping("/employees/surname/{surname}")
    public List<Employee> getBySurname(@PathVariable String surname) {
        try {
            surname = URLDecoder.decode(surname, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findEmployeesBySurname(surname);
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        try {
            name = URLDecoder.decode(name, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findEmployeesByName(name);
    }

    @GetMapping("/employees/patronymic/{patronymic}")
    public List<Employee> getByPatronymic(@PathVariable String patronymic) {
        try {
            patronymic = URLDecoder.decode(patronymic, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findEmployeesByPatronymic(patronymic);
    }

    @GetMapping("/employees/fullname/{fullname}")
    public List<Employee> getByFullName(@PathVariable String fullname) {
        try {
            fullname = URLDecoder.decode(fullname, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findEmployeesByFullName(fullname);
    }

    @GetMapping("/employees/birthday/{birthday}")
    public List<Employee> getByBirthday(@PathVariable String birthday) {
        return service.findEmployeesByBirthday(LocalDate.parse(birthday));
    }

    @GetMapping("/employees/salary/{salary}")
    public List<Employee> getBySalary(@PathVariable String salary) {
        try {
            salary = URLDecoder.decode(salary, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return service.findEmployeesBySalary(Integer.parseInt(salary));
    }

    @PostMapping("/employees")
    public Long create(@RequestBody Employee emp) {
        return service.createEmployee(emp);
    }

    @PutMapping("/employees/{id}")
    public void update(@RequestBody Employee emp, @PathVariable Long id) {
        service.updateEmployee(id, emp);
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}