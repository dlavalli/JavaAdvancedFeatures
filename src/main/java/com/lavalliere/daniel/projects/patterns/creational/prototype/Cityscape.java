package com.lavalliere.daniel.projects.patterns.creational.prototype;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.util.List;

@IsDemoable
public class Cityscape implements Demoable {

    private void createCityscape(List<Graphic> graphics) {
        System.out.println("Constructing cityscape: ");
        graphics.forEach(System.out::println);
    }

    private Cityscape buildIt() {
        BuildingType houseBuildingType = new BuildingType("House");
        Building house1 = new Building(21, houseBuildingType);
        Building house2 = house1.clone();
        Building house3 = house1.clone();
        Building house4 = house1.clone();
        Building house5 = house1.clone();
        houseBuildingType.setType("House with yard");

        BuildingType skyscraperBuildingType = new BuildingType("skyscraper");
        Building skyscraper1 = new Building(86, skyscraperBuildingType);
        Building skyscraper2 = skyscraper1.clone();
        Building skyscraper3 = skyscraper1.clone();
        Building skyscraper4 = skyscraper1.clone();
        Building skyscraper5 = skyscraper1.clone();

        List<Graphic> order = List.of(house1, house2, house3, house4, house5,
            skyscraper1, skyscraper2, skyscraper3, skyscraper4, skyscraper5);

        createCityscape(order);
        System.out.println();
        return this;
    }

    public void demo() {
        buildIt();
    }
}
