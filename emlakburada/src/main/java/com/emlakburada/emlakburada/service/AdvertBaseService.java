package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.dto.request.AdvertRequest;
import com.emlakburada.emlakburada.dto.response.AdvertResponse;
import com.emlakburada.emlakburada.model.Advert;
import com.emlakburada.emlakburada.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdvertBaseService {

    private int advertNo = 1000;

    protected AdvertResponse convertToAdvertResponse(Advert advert) {
        AdvertResponse response = new AdvertResponse();
        response.setUser(advert.getUser());
        response.setAdvertNo(advert.getAdvertNo());
        response.setTitle(advert.getTitle());
        response.setPrice(advert.getPrice());
        return response;
    }

    protected Advert convertToAdvertOne(AdvertRequest advertRequest) {
        Advert advert = new Advert();
        advertNo++;
        advert.setId(advertRequest.getId());
        advert.setAdvertNo(advertNo);
        advert.setTitle(advertRequest.getTitle());
        advert.setPrice(advertRequest.getPrice());
        advert.setDuration(advertRequest.getDuration());
        return advert;
    }

    protected Advert convertToAdvert(AdvertRequest advertRequest, Optional<User> user) {
        Advert advert = null;
        if (user.isPresent()) {
            advert = new Advert();
            advertNo++;
            advert.setUser(user.get());
            advert.setAdvertNo(advertNo);
            advert.setTitle(advertRequest.getTitle());
            advert.setPrice(advertRequest.getPrice());
            advert.setDuration(advertRequest.getDuration());
        } else {
            log.info("User not found!");
        }
        return advert;
    }
}