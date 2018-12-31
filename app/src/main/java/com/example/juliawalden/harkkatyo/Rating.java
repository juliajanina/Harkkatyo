package com.example.juliawalden.harkkatyo;

import java.io.Serializable;

public class Rating implements Serializable {

    public String food;
    public float rating;


    public Rating(String food, float rating) {
        this.food = food;
        this.rating = rating;
    }

    public String toString() {
        return rating + " ";
    }

    public String getFood() {
        return food;
    }

    public float getRating() {
        return rating;
    }

    public void saveRating() {

    }
}
