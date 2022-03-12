package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.dto.request.BannerRequest;
import com.emlakburada.emlakburadabanner.dto.response.BannerResponse;
import com.emlakburada.emlakburadabanner.model.Address;
import com.emlakburada.emlakburadabanner.model.Banner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BannerBaseService {

    protected Banner convertToBannerEntity(BannerRequest bannerRequest) {
        Banner banner = new Banner();
        banner.setAdvertNo(bannerRequest.getAdvertNo());
        banner.setPhone(bannerRequest.getPhone());
        banner.setQuantity(bannerRequest.getQuantity());
        return banner;
    }

    protected Banner convertToBanner(BannerRequest bannerRequest, Optional<Address> address) {
        Banner banner = null;
        if (address.isPresent()) {
            banner = new Banner();
            banner.setAdvertNo(bannerRequest.getAdvertNo());
            banner.setPhone(bannerRequest.getPhone());
            banner.setQuantity(bannerRequest.getQuantity());
            banner.setAddress(address.get());
        } else {
            log.info("Address not found!");
        }
        return banner;
    }

    protected BannerResponse convertToBannerResponse(Banner banner) {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setAdvertNo(banner.getAdvertNo());
        bannerResponse.setPhone(banner.getPhone());
        bannerResponse.setQuantity(banner.getQuantity());
        return bannerResponse;
    }
}