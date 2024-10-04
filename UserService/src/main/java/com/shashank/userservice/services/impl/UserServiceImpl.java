package com.shashank.userservice.services.impl;

import com.shashank.userservice.entity.Hotel;
import com.shashank.userservice.entity.Rating;
import com.shashank.userservice.entity.User;
import com.shashank.userservice.exception.ResourceNotFoundException;
import com.shashank.userservice.external.services.HotelService;
import com.shashank.userservice.repository.UserRepository;
import com.shashank.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement the logic to get all users from database from all using rest template
        //http://localhost:7073/users


        //return the list of users

        return userRepository.findAll();
    }


    @Override
    public User getUser(String userId) {
        //get user from database with the help of userRepository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id not found:  " + userId));
        //fetch rating of the above user from RATING SERVICE
        //http://localhost:7073/rating/users/{userId}
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/rating/user/" + user.getUserId(), Rating[].class);
        logger.info("forObject: " + ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());
        List<Rating> ratingList = ratings.stream().map(rating -> {
            logger.info("inside map: " + rating.getRatingId());
            //for each rating fetch hotel info
            //http://localhost:8085/hotels/{hotelId}
            //rating.setHotel(restTemplate.getForObject("http://localhost:8085/hotels/" + rating.getHotelId(), Hotel.class));
//          ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("Response status code: {} ", forEntity.getStatusCode().toString());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
