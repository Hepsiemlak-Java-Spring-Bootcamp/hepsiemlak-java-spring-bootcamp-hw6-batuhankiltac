package com.emlakburada.emlakburada.controller;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.AdvertRequest;
import com.emlakburada.emlakburada.dto.response.AdvertResponse;
import com.emlakburada.emlakburada.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertController {
    private final AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @PostMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> addAdvert(@RequestBody AdvertRequest advertRequest) {
        return new ResponseEntity<>(advertService.add(advertRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/adverts")
    public ResponseEntity<List<AdvertResponse>> getAllAdverts() {
        return new ResponseEntity<>(advertService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/adverts/{id}")
    public ResponseEntity<AdvertResponse> getAdvertById(@PathVariable Integer id) {
        return new ResponseEntity<>(advertService.getAdvertById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/adverts")
    public ResponseEntity<AdvertResponse> updateAdvert(@RequestBody AdvertRequest advertRequest) {
        return new ResponseEntity<>(advertService.update(advertRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/adverts/{id}")
    public ResponseEntity<Result> deleteAdvertById(@PathVariable Integer id) {
        return new ResponseEntity<>(advertService.deleteById(id), HttpStatus.OK);
    }
}