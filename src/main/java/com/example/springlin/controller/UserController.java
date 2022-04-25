package com.example.springlin.controller;

import com.example.springlin.model.*;
import com.example.springlin.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;

    public UserController(DiningReviewRepository diningReviewRepository, UserRepository userRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public User getProfileByName(@RequestParam String name) {
        Optional<User> userOptional = userRepository.findByName(name);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    @PostMapping("/create")
    public User createProfile(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/update")
    public User updateProfile(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if (!userOptional.isPresent()) return userRepository.save(user);
        User userToUpdate = userOptional.get();
        userToUpdate.setCity(user.getCity());
        userToUpdate.setState(user.getState());
        userToUpdate.setZipcode(user.getZipcode());
        userToUpdate.setMindDairyAllergy(user.isMindDairyAllergy());
        userToUpdate.setMindPeanutAllergy(user.isMindPeanutAllergy());
        userToUpdate.setMindEggAllergy(user.isMindEggAllergy());
        return userRepository.save(userToUpdate);
    }

    @PostMapping("/submit")
    public DiningReview submitReview(@RequestBody DiningReview review) {
        String name = review.getUserName();
        Optional<User> userOptional = userRepository.findByName(name);
        if (!userOptional.isPresent()) {
            return null;
        }
        return diningReviewRepository.save(review);
    }
}
