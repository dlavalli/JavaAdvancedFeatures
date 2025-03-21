package com.lavalliere.daniel.projects.patterns.creational.prototype;

public class BuildingType {

    private String type;

    public BuildingType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}