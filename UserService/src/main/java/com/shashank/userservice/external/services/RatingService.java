package com.shashank.userservice.external.services;


import com.shashank.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    //get


    //post
    @PostMapping("/rating")
    ResponseEntity<Rating> createRating(Rating values);


    //put
    @PutMapping("/rating/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);

    //delete
    @DeleteMapping("/rating/{ratingId}")
    void deleteRating(@PathVariable String ratingId);


}
