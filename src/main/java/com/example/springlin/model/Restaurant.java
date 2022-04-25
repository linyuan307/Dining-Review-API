package com.example.springlin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 *
 * Restaurant entity-related scenarios:
 *
 * As an application experience, I want to submit a new restaurant entry. Should a restaurant with the same name and with the same zip code already exist, I will see a failure.
 * As an application experience, I want to fetch the details of a restaurant, given its unique Id.
 * As an application experience, I want to fetch restaurants that match a given zip code and that also have at least one user-submitted score for a given allergy. I want to see them sorted in descending order.
 */


@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int zipcode;

    private Double peanutAllergyReview;
    private int numberOfUsersReviewPeanutAllergy;

    private Double eggAllergyReview;
    private int numberOfUsersReviewEggAllergy;

    private Double dairyAllergyReview;
    private int numberOfUsersReviewDairyAllergy;

    private Double overallAllergyReview;
    private long numberOfAllUsers;

    public Restaurant(String name, int zipcode) {
        this.name = name;
        this.zipcode = zipcode;

        // default
        this.peanutAllergyReview = null;
        this.numberOfUsersReviewPeanutAllergy = 0;

        this.dairyAllergyReview = null;
        this.numberOfUsersReviewDairyAllergy = 0;

        this.eggAllergyReview = null;
        this.numberOfUsersReviewEggAllergy = 0;

        this.overallAllergyReview = null;
        this.numberOfAllUsers = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Double getPeanutAllergyReview() {
        return peanutAllergyReview;
    }

    public void setPeanutAllergyReview(Double peanutAllergyReview) {
        this.peanutAllergyReview = peanutAllergyReview;
    }

    public int getNumberOfUsersReviewPeanutAllergy() {
        return numberOfUsersReviewPeanutAllergy;
    }

    public void setNumberOfUsersReviewPeanutAllergy(int numberOfUsersReviewPeanutAllergy) {
        this.numberOfUsersReviewPeanutAllergy = numberOfUsersReviewPeanutAllergy;
    }

    public Double getEggAllergyReview() {
        return eggAllergyReview;
    }

    public void setEggAllergyReview(Double eggAllergyReview) {
        this.eggAllergyReview = eggAllergyReview;
    }

    public int getNumberOfUsersReviewEggAllergy() {
        return numberOfUsersReviewEggAllergy;
    }

    public void setNumberOfUsersReviewEggAllergy(int numberOfUsersReviewEggAllergy) {
        this.numberOfUsersReviewEggAllergy = numberOfUsersReviewEggAllergy;
    }

    public Double getDairyAllergyReview() {
        return dairyAllergyReview;
    }

    public void setDairyAllergyReview(Double dairyAllergyReview) {
        this.dairyAllergyReview = dairyAllergyReview;
    }

    public int getNumberOfUsersReviewDairyAllergy() {
        return numberOfUsersReviewDairyAllergy;
    }

    public void setNumberOfUsersReviewDairyAllergy(int numberOfUsersReviewDairyAllergy) {
        this.numberOfUsersReviewDairyAllergy = numberOfUsersReviewDairyAllergy;
    }

    public Double getOverallAllergyReview() {
        return overallAllergyReview;
    }

    public void setOverallAllergyReview(Double overallAllergyReview) {
        this.overallAllergyReview = overallAllergyReview;
    }

    public long getNumberOfAllUsers() {
        return numberOfAllUsers;
    }

    public void setNumberOfAllUsers(long numberOfAllUsers) {
        this.numberOfAllUsers = numberOfAllUsers;
    }
}
