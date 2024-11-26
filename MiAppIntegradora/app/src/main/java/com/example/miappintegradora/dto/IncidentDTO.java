package com.example.miappintegradora.dto;

public class IncidentDTO {
    private int id;
    private String description;
    private String status;

    // Constructor
    public IncidentDTO(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
