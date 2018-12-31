package com.example.juliawalden.harkkatyo;

import java.io.Serializable;

public class Review implements Serializable {

    private float rating;
    private String comment;
    private String food;
    private String restaurant;

    //protected ArrayList<Review> reviews = new ArrayList<>();

    public Review(float rating, String comment, String restaurant, String food) {
        this.rating = rating;
        this.comment = comment;
        this.food = food;
        this.restaurant = restaurant;

    }

    public Review() {

    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getFood() {
        return food;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String toString() {
        return "Ravintola: " + restaurant + "\nRuoka: " + food + "\nArvosana: " + rating + "\nKommentti: " + comment;
    }

    //public ArrayList<Review> getReviews() {
    //return reviews;
    //}

    public void addReviews(float rating, String comment, String restaurant, String food) {
        this.rating = rating;
        this.comment = comment;
        this.food = food;
        this.restaurant = restaurant;
    }


}
