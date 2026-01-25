package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.externalServices.HotelServiceClient;
import com.lcwd.user.service.externalServices.RatingServiceClient;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    private HotelServiceClient hotelServiceClient;

    @Autowired
    private RatingServiceClient ratingServiceClient;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        try {
            logger.info("Inside getAllUsers UserServiceImpl-------->");

            for (User user : users) {
                logger.info("Inside for Loop getAllUsers UserServiceImpl-------->");
                //ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), ArrayList.class);
                Rating[] ratings = ratingServiceClient.getAllRatingsByUserId(user.getUserId());
                List<Rating> userRatings = Arrays.stream(ratings).toList();

                List<Rating> userRatings2 = userRatings.stream().map(rating -> {
                    Hotel hotel = hotelServiceClient.getHotelById(rating.getHotelId());
                    rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());

                user.setRatings(userRatings);
            }
        }catch (ResourceNotFoundException e){
            logger.info("Inside catch getAllUsers UserServiceImpl-------->"+ e);
        }

        return users;
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
        //fetch rating of above user
        //Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        Rating[] ratingsOfUser = ratingServiceClient.getAllRatingsByUserId(userId);
       // logger.info("User Ratings ::: {}", Arrays.stream(ratingsOfUser).toList().toString());

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelServiceClient.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);

            return  rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public String updateUser(User user) {
        return "";
    }
}
