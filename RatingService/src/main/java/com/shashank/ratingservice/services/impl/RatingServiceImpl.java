package com.shashank.ratingservice.services.impl;


import com.shashank.ratingservice.entity.Rating;
import com.shashank.ratingservice.repository.RatingRepository;
import com.shashank.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
