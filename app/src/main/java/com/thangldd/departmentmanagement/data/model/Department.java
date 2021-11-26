package com.thangldd.departmentmanagement.data.model;

import androidx.annotation.NonNull;

public class Department {
    private String id;
    private String name;
    private String description;

    public Department() {
    }

    public Department(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return id + '\t' + name;
    }
}
