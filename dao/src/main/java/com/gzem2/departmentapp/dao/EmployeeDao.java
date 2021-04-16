package com.gzem2.departmentapp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import com.gzem2.departmentapp.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

@Component
public class EmployeeDao implements IEmployeeDao {

    public class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getLong("id"));
            emp.setDepartmentId(rs.getLong("department_id"));
            emp.setSurname(rs.getString("surname"));
            emp.setName(rs.getString("name"));
            emp.setPatronymic(rs.getString("patronymic"));
            emp.setBirthday(rs.getDate("birthday").toLocalDate());
            emp.setSalary(rs.getInt("salary"));
            return emp;
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long createEmployee(Employee emp) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) VALUES(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, emp.getDepartmentId());
            ps.setString(2, emp.getSurname());
            ps.setString(3, emp.getName());
            ps.setString(4, emp.getPatronymic());
            ps.setDate(5, Date.valueOf(emp.getBirthday()));
            ps.setInt(6, emp.getSalary());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id = ?", new EmployeeMapper(),
                    new Object[] { id });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Long id) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE department_id = ?",
                    new EmployeeMapper(), new Object[] { id });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesBySurname(String surname) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE surname = ?", new EmployeeMapper(),
                    new Object[] { surname });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesByName(String name) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE name = ?", new EmployeeMapper(),
                    new Object[] { name });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesByPatronymic(String patronymic) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE patronymic = ?",
                    new EmployeeMapper(), new Object[] { patronymic });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesByFullName(String fullname) {
        try {
            String[] temp = fullname.split(" ");
            String surname = temp[0];
            String name = temp[1];
            String patronymic = temp[2];
            List<Employee> lem = jdbcTemplate.query(
                    "SELECT * FROM employees WHERE surname = ? AND name = ? AND patronymic = ?", new EmployeeMapper(),
                    new Object[] { surname, name, patronymic });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesByBirthday(LocalDate birthday) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE birthday = ?", new EmployeeMapper(),
                    new Object[] { Date.valueOf(birthday) });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesBySalary(Integer salary) {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees WHERE salary = ?", new EmployeeMapper(),
                    new Object[] { salary });
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        try {
            List<Employee> lem = jdbcTemplate.query("SELECT * FROM employees", new EmployeeMapper());
            return lem;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateEmployee(Long id, Employee emp) {
        jdbcTemplate.update(
                "UPDATE employees set id=?, department_id=?, surname=?, name=?, patronymic=?, birthday=?, salary=? where id=?",
                new Object[] { emp.getId(), emp.getDepartmentId(), emp.getSurname(), emp.getName(), emp.getPatronymic(),
                        emp.getBirthday(), emp.getSalary(), id });
    }

    @Override
    public void deleteEmployee(Long id) {
        jdbcTemplate.update("DELETE FROM employees WHERE id = ?", new Object[] { id });
    }
}
