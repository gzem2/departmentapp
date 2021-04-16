package com.gzem2.departmentapp.model;

import java.time.LocalDate;

public class Employee {
    private Long id;
    private Long departmentId;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthday;
    private Integer salary;

    public Employee() {}

    public Employee(Long departmentId, String surname, String name, String patronymic, LocalDate birthday,
            Integer salary) {
        this(null, departmentId, surname, name, patronymic, birthday, salary);
    }

    public Employee(Long id, Long departmentId, String surname, String name, String patronymic,
            LocalDate birthday, Integer salary) {
        this.setId(id);
        this.setDepartmentId(departmentId);
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setBirthday(birthday);
        this.setSalary(salary);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" + " departmentId='" + getDepartmentId() + "'" + ", surname='" + getSurname() + "'" + ", name='"
                + getName() + "'" + ", patronymic='" + getPatronymic() + "'" + ", birthday='" + getBirthday() + "'"
                + ", salary='" + getSalary() + "'" + "}";
    }
}
