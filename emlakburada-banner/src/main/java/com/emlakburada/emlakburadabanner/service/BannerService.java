package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.BannerRequest;
import com.emlakburada.emlakburadabanner.dto.response.BannerResponse;

import java.util.List;

public interface BannerService {
    List<BannerResponse> getAll();
    BannerResponse getBannerById(Integer id);
    BannerResponse add(BannerRequest bannerRequest);
    BannerResponse update(BannerRequest bannerRequest);
    Result deleteById(Integer id);
}