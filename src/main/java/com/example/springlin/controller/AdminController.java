package com.example.springlin.controller;

import com.example.springlin.model.*;
import com.example.springlin.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final DiningReviewRepository diningReviewRepository;

    public AdminController(DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }

    // get a list of reviews that are pending
    @GetMapping("/")
    public List<DiningReview> getPendingReviews() {
        List<DiningReview> reviewList = diningReviewRepository.findAll();
        List<DiningReview> pendingReviews = new ArrayList<>();
        for (DiningReview review : reviewList) {
            if (review.getStatus() == Status.PENDING) {
                pendingReviews.add(review);
            }
        }

        return pendingReviews;
    }
    // action to accept or reject review
    @PutMapping("/action")
    public DiningReview actionOnReview(@RequestParam(name = "id", required = true) long reviewId, @RequestParam(
            name = "action", required = false, defaultValue = "") String action) {
        boolean isAccepted = action.equals("accept");
        Optional<DiningReview> reviewOptional = diningReviewRepository.findById(reviewId);
        if (!reviewOptional.isPresent()) return null;
        DiningReview reviewToUpdate = reviewOptional.get();
        if (isAccepted) {
            reviewToUpdate.setStatus(Status.ACCEPTED);
        } else {
            reviewToUpdate.setStatus(Status.REJECTED);
        }
        return diningReviewRepository.save(reviewToUpdate);
    }
}
