package com.lcwd.user.service;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.externalServices.RatingServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RatingServiceClient ratingServiceClient;

    @Test
    void contextLoads() {
    }

    @Test
    void createRating()
    {
        Rating rating = Rating.builder().userId("Testingid").hotelId("ayvajcnacnbuasb").ratingId("helloworld").build();
        ratingServiceClient.createRating(rating);
    }

}
