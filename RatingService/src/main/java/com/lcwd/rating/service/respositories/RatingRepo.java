package com.lcwd.rating.service.respositories;

import com.lcwd.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);
    List<Rating> findByUserId(String userId);
}
