package com.emlakburada.emlakburadabanner.controller;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.BannerRequest;
import com.emlakburada.emlakburadabanner.dto.response.BannerResponse;
import com.emlakburada.emlakburadabanner.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping(value = "/banners")
    public ResponseEntity<BannerResponse> addBanner(@RequestBody BannerRequest bannerRequest) {
        return new ResponseEntity<>(bannerService.add(bannerRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/banners")
    public ResponseEntity<List<BannerResponse>> getAllBanners() {
        return new ResponseEntity<>(bannerService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/banners/{id}")
    public ResponseEntity<BannerResponse> getBannerById(@PathVariable Integer id) {
        return new ResponseEntity<>(bannerService.getBannerById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/banners")
    public ResponseEntity<BannerResponse> updateBanner(@RequestBody BannerRequest bannerRequest) {
        return new ResponseEntity<>(bannerService.update(bannerRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/banners/{id}")
    public ResponseEntity<Result> deleteBannerById(@PathVariable Integer id) {
        return new ResponseEntity<>(bannerService.deleteById(id), HttpStatus.OK);
    }
}