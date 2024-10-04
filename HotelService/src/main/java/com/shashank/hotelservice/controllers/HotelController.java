package com.shashank.hotelservice.controllers;

import com.shashank.hotelservice.entity.Hotel;
import com.shashank.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.createHotel(hotel));
    }


    //getall

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }


    //get
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> get(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.get(id));
    }
}
