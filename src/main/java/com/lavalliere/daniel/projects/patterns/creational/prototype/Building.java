package com.lavalliere.daniel.projects.patterns.creational.prototype;

public class Building extends Graphic {

    private BuildingType buildingType;

    public Building(int heightInPixels, BuildingType buildingType) {
        super(heightInPixels);
        this.buildingType = buildingType;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }


    @Override
    public String toString() {
        return "Building{" +
            "height in pixels='" + super.getHeightInPixels() + '\'' +
            "building type='" + buildingType + '\'' +
            '}';
    }

    @Override
    public Building clone() {
        return new Building(
            this.getHeightInPixels(),  // basic types are ok since by copy not ref
            new BuildingType(this.getBuildingType().getType())  // Prevent shallow copy unless you want changes propagated to all clones
        );
    }

}