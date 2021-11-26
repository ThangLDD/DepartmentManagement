package com.thangldd.departmentmanagement.data.model;

import androidx.annotation.NonNull;

public class Employee {
    private String id;
    private String name;
    private String dateOB;
    private String hometown;
    private String department;

    public Employee() {
    }

    public Employee(String id, String name, String dateOB, String hometown) {
        this.id = id;
        this.name = name;
        this.dateOB = dateOB;
        this.hometown = hometown;
    }

    public Employee(String id, String name, String dateOB, String hometown, String department) {
        this.id = id;
        this.name = name;
        this.dateOB = dateOB;
        this.hometown = hometown;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOB() {
        return dateOB;
    }

    public String getHometown() {
        return hometown;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @NonNull
    @Override
    public String toString() {
        if (department != null) {
            return id + " - " + name + " - " + dateOB + " - " + hometown + " - " + department;
        }
        return id + " - " + name + " - " + dateOB + " - " + hometown;
    }
}
