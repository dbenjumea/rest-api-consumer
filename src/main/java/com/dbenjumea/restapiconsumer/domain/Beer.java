package com.dbenjumea.restapiconsumer.domain;

import java.io.Serializable;

public class Beer implements Serializable{

    private Long id;
    private String name;
    private String description;
    private Double abv;
    private String location;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", abv=" + abv +
                ", location='" + location + '\'' +
                '}';
    }
}
