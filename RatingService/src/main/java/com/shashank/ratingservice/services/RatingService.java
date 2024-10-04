package com.shashank.ratingservice.services;

import com.shashank.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();


    //get rating by user id how many times rated
    List<Rating> getRatingsByUser(String userId);


    //get rating by hotel id how many times rated

    List<Rating> getRatingsByHotel(String hotelId);
}
