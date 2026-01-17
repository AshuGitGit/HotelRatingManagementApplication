package com.lcwd.hotel.service.services;

import com.lcwd.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create Hotel
    Hotel createHotel(Hotel hotel);

    //get All Hotels
    List<Hotel> getAllHotels();

    //get hotel By Id
    Hotel getHotelById(String id);
}
