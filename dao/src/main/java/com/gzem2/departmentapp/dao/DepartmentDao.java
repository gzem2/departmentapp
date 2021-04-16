package com.gzem2.departmentapp.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gzem2.departmentapp.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDao implements IDepartmentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long createDepartment(Department dep) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = dep.getId() == null ? "INSERT INTO departments(department_name) VALUES(?)"
                : "INSERT INTO departments(id, department_name) VALUES(?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (dep.getId() == null) {
                ps.setString(1, dep.getDepartmentName());
            } else {
                ps.setLong(1, dep.getId());
                ps.setString(2, dep.getDepartmentName());
            }
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Department findDepartmentById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM departments WHERE id = ?",
                    (rs, rowNum) -> new Department(rs.getLong("id"), rs.getString("department_name")),
                    new Object[] { id });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Department> findDepartmentsByName(String departmentName) {
        try {
            List<Department> ld = new ArrayList<Department>();
            jdbcTemplate.query("SELECT * FROM departments WHERE department_name = ?",
                    (rs, rowNum) -> ld.add(new Department(rs.getLong("id"), rs.getString("department_name"))),
                    new Object[] { departmentName });
            return ld;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Department> findAllDepartments() {
        try {
            List<Department> ld = new ArrayList<Department>();
            jdbcTemplate.query("SELECT * FROM departments",
                    (rs, rowNum) -> ld.add(new Department(rs.getLong("id"), rs.getString("department_name"))));
            return ld;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateDepartment(Long id, Department dep) {
        jdbcTemplate.update("UPDATE departments set id=?, department_name=? where id=?",
                new Object[] { dep.getId(), dep.getDepartmentName(), id });
    }

    @Override
    public void deleteDepartment(Long id) {
        jdbcTemplate.update("DELETE FROM departments WHERE id = ?", new Object[] { id });
    }

}
