package com.example.springlin.model;

public class AdminReview {
    public static void action(DiningReview review, Status status) {
        review.setStatus(status);
    }
}
