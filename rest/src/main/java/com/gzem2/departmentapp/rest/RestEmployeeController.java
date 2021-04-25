package com.gzem2.departmentapp.rest;

import com.gzem2.departmentapp.model.Employee;
import com.gzem2.departmentapp.service.IEmployeeService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
class RestEmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping("/employees/average/{id}")
    public Integer getAverageSalaryByDepartment(@PathVariable Long id) {
        Integer avg = service.findEmployeeAverageSalaryByDepartment(id);
        if(avg == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return avg;
        }
    }

    @GetMapping("/employees/bornbetween/{dateFrom}/{dateTo}")
    public List<Employee> getBornBetween(@PathVariable String dateFrom, @PathVariable String dateTo) {
        List<Employee> emps = service.findEmployeesBornBetween(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        List<Employee> emps = service.findAllEmployees();
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable Long id) {
        Employee emp = service.findEmployeeById(id);
        if(emp == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emp;
        }
    }

    @GetMapping("/employees/department/{id}")
    public List<Employee> getByDepartmentId(@PathVariable Long id) {
        List<Employee> emps = service.findEmployeesByDepartmentId(id);
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/surname/{surname}")
    public List<Employee> getBySurname(@PathVariable String surname) {
        try {
            surname = URLDecoder.decode(surname, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Employee> emps = service.findEmployeesBySurname(surname);
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        try {
            name = URLDecoder.decode(name, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Employee> emps = service.findEmployeesByName(name);
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/patronymic/{patronymic}")
    public List<Employee> getByPatronymic(@PathVariable String patronymic) {
        try {
            patronymic = URLDecoder.decode(patronymic, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Employee> emps = service.findEmployeesByPatronymic(patronymic);
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/fullname/{fullname}")
    public List<Employee> getByFullName(@PathVariable String fullname) {
        try {
            fullname = URLDecoder.decode(fullname, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Employee> emps = service.findEmployeesByFullName(fullname);
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/birthday/{birthday}")
    public List<Employee> getByBirthday(@PathVariable String birthday) {
        List<Employee> emps = service.findEmployeesByBirthday(LocalDate.parse(birthday));
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @GetMapping("/employees/salary/{salary}")
    public List<Employee> getBySalary(@PathVariable String salary) {
        try {
            salary = URLDecoder.decode(salary, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Employee> emps = service.findEmployeesBySalary(Integer.parseInt(salary));
        if(emps == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return emps;
        }
    }

    @PostMapping("/employees")
    public Long create(@RequestBody Employee emp) { 
        Long id = service.createEmployee(emp);
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return id;
        }
    }

    @PutMapping("/employees/{id}")
    public void update(@RequestBody Employee emp, @PathVariable Long id) {
        try {
            service.updateEmployee(id, emp);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}