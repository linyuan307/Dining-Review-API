package com.example.springlin.repository;

import com.example.springlin.model.DiningReview;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    Optional<DiningReview> findById(Long id);
    List<DiningReview> findByRestaurantId(Long restaurantId);
    List<DiningReview> findAll();
}
