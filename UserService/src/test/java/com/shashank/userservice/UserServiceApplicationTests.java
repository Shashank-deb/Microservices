package com.shashank.userservice;

import com.shashank.userservice.entity.Rating;
import com.shashank.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoads() {
    }


    @Test
    void createRating() {
        Rating rating = Rating.builder().ratingId("10").userId("").hotelId("").feedback("This is using feign client").build();
        ResponseEntity<Rating> saveRating = ratingService.createRating(rating);
        HttpStatusCode statusCode = saveRating.getStatusCode();
        System.out.println("Status code = " + statusCode);
        System.out.println("New Rating created  = " + saveRating);
    }

}
