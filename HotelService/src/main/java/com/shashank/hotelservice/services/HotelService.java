package com.shashank.hotelservice.services;

import com.shashank.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel createHotel(Hotel hotel);
    //getall
    List<Hotel> getAll();
    //get single

    Hotel get(String id);
}
