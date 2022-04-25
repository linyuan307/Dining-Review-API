package com.example.springlin.repository;

import com.example.springlin.model.Restaurant;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findById(Long id);
    List<Restaurant>findByZipcode(int zipcode);
    Optional<Restaurant> findByNameAndZipcode(String name, int zipcode);
}
