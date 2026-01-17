package com.lcwd.rating.service.services;

import com.lcwd.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    //crete Rating
    Rating createRating(Rating rating);

    //get All Ratings
    List<Rating> getAllRatings();

    //get All By UserId
    List<Rating> getRatingsByUserId(String userId);

    //get ALl By HotelId
    List<Rating> getRatingsByHotelId(String hotelId);

}
