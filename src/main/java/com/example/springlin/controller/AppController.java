package com.example.springlin.controller;

import org.springframework.web.bind.annotation.*;

import com.example.springlin.repository.*;
import com.example.springlin.model.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("app")
public class AppController {
    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;

    public AppController(RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String greeting() {
        return "Hello";
    }

    // fetch a restaurant by id
    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (!restaurantOptional.isPresent()) return null;
        return restaurantOptional.get();
    }

    // fetch a list of restaurant matching zipcode and at least one review
    @GetMapping("/{zipcode}")
    public List<Restaurant> getRestaurantMatchZipcode(@PathVariable int zipcode) {
        List<Restaurant> restaurantList = restaurantRepository.findByZipcode(zipcode);
        List<Restaurant> restaurantListAtLeastOneReview = new ArrayList<>();
        for (Restaurant rest : restaurantList) {
            if (rest.getNumberOfAllUsers() > 0) {
                restaurantListAtLeastOneReview.add(rest);
            }
        }
        return restaurantListAtLeastOneReview;
    }

    // submit a restaurant entity, if the name with zipcode exists, failure to submit
    @PostMapping("/submit")
    public Restaurant submitRestaurantEntity(@RequestBody Restaurant restaurant) {
        String name = restaurant.getName();
        int zipcode = restaurant.getZipcode();
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByNameAndZipcode(name, zipcode);
        if (restaurantOptional.isPresent()) return null;
        return restaurantRepository.save(restaurant);
    }

    // validate a review by verifying a user name on user name on the review
    @PutMapping("/validate")
    public DiningReview validateReview(@RequestBody DiningReview review) {
        Optional<User> userOptional = userRepository.findByName(review.getUserName());
        if (userOptional.isPresent()) {
            review.setStatus(Status.ACCEPTED);
        } else {
            review.setStatus(Status.REJECTED);
        }
        return diningReviewRepository.save(review);
    }


    // update a restaurant review by fetch all approved reviews related with the restaurant and update the review score
    @PutMapping("/update")
    public Restaurant updateRestaurantReview(@RequestParam Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (!restaurantOptional.isPresent()) return null;
        List<DiningReview> reviewList = diningReviewRepository.findByRestaurantId(id);

        double peanutAllergyScore = 0;
        int numberOfPeanutAllergyReviews = 0;
        double eggAllergyScore = 0;
        int numberOfEggAllergyReviews = 0;
        double dairyAllergyScore = 0;
        int numberOfDairyAllergyReviews = 0;

        for (DiningReview review: reviewList) {
            if (review.getStatus() == Status.ACCEPTED) {
                if (review.getPeanutAllergyReview() != null) {
                    peanutAllergyScore += review.getPeanutAllergyReview();
                    numberOfPeanutAllergyReviews++;
                }
                if (review.getEggAllergyReview() != null) {
                    eggAllergyScore += review.getEggAllergyReview();
                    numberOfEggAllergyReviews++;
                }
                if (review.getDairyAllergyReview() != null) {
                    dairyAllergyScore += review.getDairyAllergyReview();
                    numberOfDairyAllergyReviews++;
                }
            }
        }

        Restaurant restaurantToUpdate = restaurantOptional.get();

        double avgPeanut = numberOfPeanutAllergyReviews == 0 ? 0 : peanutAllergyScore / numberOfPeanutAllergyReviews;
        restaurantToUpdate.setPeanutAllergyReview(avgPeanut);
        restaurantToUpdate.setNumberOfUsersReviewPeanutAllergy(numberOfPeanutAllergyReviews);

        double avgEgg = numberOfEggAllergyReviews == 0 ? 0 : eggAllergyScore / numberOfEggAllergyReviews;
        restaurantToUpdate.setEggAllergyReview(avgEgg);
        restaurantToUpdate.setNumberOfUsersReviewEggAllergy(numberOfEggAllergyReviews);

        double avgDairy = numberOfDairyAllergyReviews == 0 ? 0 : dairyAllergyScore / numberOfDairyAllergyReviews;
        restaurantToUpdate.setDairyAllergyReview(avgDairy);
        restaurantToUpdate.setNumberOfUsersReviewDairyAllergy(numberOfDairyAllergyReviews);


        int totalUsers = numberOfPeanutAllergyReviews + numberOfEggAllergyReviews + numberOfDairyAllergyReviews;
        double totalScore = peanutAllergyScore + eggAllergyScore + dairyAllergyScore;
        double avgScore = totalUsers == 0 ? 0 : totalScore / totalUsers;
        restaurantToUpdate.setOverallAllergyReview(avgScore);
        restaurantToUpdate.setNumberOfAllUsers(totalUsers);

        return restaurantRepository.save(restaurantToUpdate);
    }
}
