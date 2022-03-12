package com.emlakburada.emlakburadabanner.controller;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import com.emlakburada.emlakburadabanner.dto.response.AddressResponse;
import com.emlakburada.emlakburadabanner.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(value = "/addresses")
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest) {
        return new ResponseEntity<>(addressService.add(addressRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/addresses/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Integer id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/addresses")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressRequest addressRequest) {
        return new ResponseEntity<>(addressService.update(addressRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/addresses/{id}")
    public ResponseEntity<Result> deleteAddressById(@PathVariable Integer id) {
        return new ResponseEntity<>(addressService.deleteById(id), HttpStatus.OK);
    }
}