package com.gzem2.departmentapp.model;

public class Department {

    private Long id;
    private String departmentName;

    public Department() {}

    public Department(String departmentName) {
        this(null, departmentName);
    }

    public Department(Long id, String departmentName) {
        this.setId(id);
        this.setDepartmentName(departmentName);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", departmentName='" + getDepartmentName() + "'" + "}";
    }
}
