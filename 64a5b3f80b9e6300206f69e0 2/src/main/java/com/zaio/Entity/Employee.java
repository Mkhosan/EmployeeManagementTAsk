package com.zaio.Entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    private Long id;
    private String name;
    private String designation;
    private double salary;
    private String department;

    public Employee() {
    }

    public Employee(Long id, String name, String designation, double salary, String department) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
