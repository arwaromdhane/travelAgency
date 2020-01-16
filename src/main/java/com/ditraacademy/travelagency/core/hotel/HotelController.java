package com.ditraacademy.travelagency.core.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    @Autowired
    HotelServices hotelServices;

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel (@RequestBody Hotel hotel) {
        return hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> getAllHotels(){
        return hotelServices.getAllHotels();
    }

}
