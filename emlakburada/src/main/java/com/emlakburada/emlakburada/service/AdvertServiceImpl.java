package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.client.BannerClient;
import com.emlakburada.emlakburada.client.request.BannerRequest;
import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.AdvertRequest;
import com.emlakburada.emlakburada.dto.response.AdvertResponse;
import com.emlakburada.emlakburada.model.Advert;
import com.emlakburada.emlakburada.model.User;
import com.emlakburada.emlakburada.queue.QueueService;
import com.emlakburada.emlakburada.repository.AdvertRepository;
import com.emlakburada.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;
    private final BannerClient bannerClient;
    private final QueueService queueService;
    private final AdvertBaseService advertBaseService;

    @Autowired
    public AdvertServiceImpl(AdvertRepository advertRepository, UserRepository userRepository, BannerClient bannerClient, QueueService queueService, AdvertBaseService advertBaseService) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
        this.bannerClient = bannerClient;
        this.queueService = queueService;
        this.advertBaseService = advertBaseService;
    }

    private final BannerRequest bannerRequest = new BannerRequest();

    @Override
    public List<AdvertResponse> getAll() {
        List<AdvertResponse> advertList = new ArrayList<>();
        for (Advert advert : advertRepository.findAll()) {
            advertList.add(advertBaseService.convertToAdvertResponse(advert));
        }
        return advertList;
    }

    @Override
    public AdvertResponse getAdvertById(Integer id) {
        Optional<Advert> advert = advertRepository.findById(id);
        return advertBaseService.convertToAdvertResponse(advert.get());
    }

    @Override
    public AdvertResponse add(AdvertRequest advertRequest) {
        Optional<User> found = userRepository.findById(advertRequest.getUserId());
        Advert advert = advertBaseService.convertToAdvert(advertRequest, found);
        String email = "batuhan@gmail.com" + advertRequest.getUserId();
        queueService.sendMessage(email);
        bannerClient.saveBanner(bannerRequest);
        return advertBaseService.convertToAdvertResponse(advertRepository.save(advert));
    }

    @Override
    public AdvertResponse update(AdvertRequest advertRequest) {
        getAdvertById(advertRequest.getId());
        Advert advert = advertBaseService.convertToAdvertOne(advertRequest);
        return advertBaseService.convertToAdvertResponse(advertRepository.save(advert));
    }

    @Override
    public Result deleteById(Integer id) {
        getAdvertById(id);
        advertRepository.deleteById(id);
        return new Result(true, "Advert has been deleted.");
    }
}