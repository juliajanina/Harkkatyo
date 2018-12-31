package com.example.juliawalden.harkkatyo;

public class Food {

    private String foodName;


    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String toString() {
        return foodName;
    }

    public String getFoodName() {
        return foodName;
    }
}