package com.example.todolistapp.models;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private Boolean status;
    private Date dueDate;

    private String label;

    public Task() {
    }

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public  Date getDueDate(){ return dueDate; }
    public String getLabel() {
        return label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}