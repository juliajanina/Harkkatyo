package com.example.juliawalden.harkkatyo;

import java.util.ArrayList;

public class University {

    protected ArrayList<Restaurant> restaurants = new ArrayList<>();


    public University() {
        restaurants.add(new Restaurant("Valitse ruokala"));
        restaurants.add(new Restaurant("Ylioppilastalo"));
        restaurants.add(new Restaurant("Laseri"));
        restaurants.add(new Restaurant("LUT Buffet"));

    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
