package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.AdvertRequest;
import com.emlakburada.emlakburada.dto.response.AdvertResponse;

import java.util.List;

public interface AdvertService {
    List<AdvertResponse> getAll();
    AdvertResponse getAdvertById(Integer id);
    AdvertResponse add(AdvertRequest advertRequest);
    AdvertResponse update(AdvertRequest advertRequest);
    Result deleteById(Integer id);
}