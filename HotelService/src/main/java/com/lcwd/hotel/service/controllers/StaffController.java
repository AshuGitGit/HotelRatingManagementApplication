package com.lcwd.hotel.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    ResponseEntity<List<String>> getAllStaff()
    {
        List<String> staff = Arrays.asList("Ramu","Shyaamu","Bunty","Pinky");

        return new ResponseEntity<>(staff, HttpStatus.OK);
    }
}
