package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.BannerRequest;
import com.emlakburada.emlakburadabanner.dto.response.BannerResponse;
import com.emlakburada.emlakburadabanner.model.Banner;
import com.emlakburada.emlakburadabanner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerBaseService bannerBaseService;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository, BannerBaseService bannerBaseService) {
        this.bannerRepository = bannerRepository;
        this.bannerBaseService = bannerBaseService;
    }

    @Override
    public List<BannerResponse> getAll() {
        List<BannerResponse> bannerList = new ArrayList<>();
        for (Banner banner : bannerRepository.findAll()) {
            bannerList.add(bannerBaseService.convertToBannerResponse(banner));
        }
        return bannerList;
    }

    @Override
    public BannerResponse getBannerById(Integer id) {
        Optional<Banner> banner = bannerRepository.findById(id);
        return bannerBaseService.convertToBannerResponse(banner.get());
    }

    @Override
    public BannerResponse add(BannerRequest bannerRequest) {
        Banner banner = bannerBaseService.convertToBannerEntity(bannerRequest);
        return bannerBaseService.convertToBannerResponse(bannerRepository.save(banner));
    }

    @Override
    public BannerResponse update(BannerRequest bannerRequest) {
        getBannerById(bannerRequest.getId());
        Banner banner = bannerBaseService.convertToBannerEntity(bannerRequest);
        return bannerBaseService.convertToBannerResponse(bannerRepository.save(banner));
    }

    @Override
    public Result deleteById(Integer id) {
        getBannerById(id);
        bannerRepository.deleteById(id);
        return new Result(true, "Banner has been deleted.");
    }
}