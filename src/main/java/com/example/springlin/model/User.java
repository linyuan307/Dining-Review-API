package com.example.springlin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

/**
 * As an unregistered user, I want to create my user profile using a display name that’s unique only to me.
 * As a registered user, I want to update my user profile. I cannot modify my unique display name.
 * As an application experience, I want to fetch the user profile belonging to a given display name.
 * As part of the backend process that validates a user-submitted dining review,
 *   I want to verify that the user exists, based on the user display name associated with the dining review.
 */

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private final String name;
    private String city;
    private String state;
    private int zipcode;
    private int reviewCount;

    private boolean mindPeanutAllergy;
    private boolean mindEggAllergy;
    private boolean mindDairyAllergy;

    public User(String name, String city, String state, int zipcode, boolean mindPeanutAllergy, boolean mindEggAllergy, boolean mindDairyAllergy) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.mindPeanutAllergy = mindPeanutAllergy;
        this.mindEggAllergy = mindEggAllergy;
        this.mindDairyAllergy = mindDairyAllergy;
        this.reviewCount = 0;
    }


    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isMindPeanutAllergy() {
        return mindPeanutAllergy;
    }

    public void setMindPeanutAllergy(boolean mindPeanutAllergy) {
        this.mindPeanutAllergy = mindPeanutAllergy;
    }

    public boolean isMindEggAllergy() {
        return mindEggAllergy;
    }

    public void setMindEggAllergy(boolean mindEggAllergy) {
        this.mindEggAllergy = mindEggAllergy;
    }

    public boolean isMindDairyAllergy() {
        return mindDairyAllergy;
    }

    public void setMindDairyAllergy(boolean mindDairyAllergy) {
        this.mindDairyAllergy = mindDairyAllergy;
    }
}
