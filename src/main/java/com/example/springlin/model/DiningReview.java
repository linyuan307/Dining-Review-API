package com.example.springlin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

/**
 *
 *
 * Dining review entity-related scenarios:
 *
 * As a registered user, I want to submit a dining review.
 * As an admin, I want to get the list of all dining reviews that are pending approval.
 * As an admin, I want to approve or reject a given dining review.
 * As part of the backend process that updates a restaurant’s set of scores, I want to fetch the set of all approved dining reviews belonging to this restaurant.
 *
 */


@Entity
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private long restaurantId;

    private Double peanutAllergyReview;

    private Double eggAllergyReview;

    private Double dairyAllergyReview;

    private String commentary;

    private Status status;

    public DiningReview(String userName, long restaurantId, Double peanutAllergyReview, Double eggAllergyReview, Double dairyAllergyReview, String commentary) {
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.peanutAllergyReview = peanutAllergyReview;
        this.eggAllergyReview = eggAllergyReview;
        this.dairyAllergyReview = dairyAllergyReview;
        this.commentary = commentary;
        this.status = Status.PENDING;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getPeanutAllergyReview() {
        return peanutAllergyReview;
    }

    public void setPeanutAllergyReview(Double peanutAllergyReview) {
        this.peanutAllergyReview = peanutAllergyReview;
    }

    public Double getEggAllergyReview() {
        return eggAllergyReview;
    }

    public void setEggAllergyReview(Double eggAllergyReview) {
        this.eggAllergyReview = eggAllergyReview;
    }

    public Double getDairyAllergyReview() {
        return dairyAllergyReview;
    }

    public void setDairyAllergyReview(Double dairyAllergyReview) {
        this.dairyAllergyReview = dairyAllergyReview;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
