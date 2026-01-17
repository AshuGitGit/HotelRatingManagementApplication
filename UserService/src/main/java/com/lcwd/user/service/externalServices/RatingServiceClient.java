package com.lcwd.user.service.externalServices;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="RATING-SERVICE")
public interface RatingServiceClient {

    @GetMapping("/ratings/users/{userId}")
    Rating[] getAllRatingsByUserId(@PathVariable String userId);

    @PostMapping("/ratings/createRating")
    Rating createRating(Rating rating);
}
