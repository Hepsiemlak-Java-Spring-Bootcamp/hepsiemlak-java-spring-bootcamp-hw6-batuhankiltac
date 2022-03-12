package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import com.emlakburada.emlakburadabanner.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAll();
    AddressResponse getAddressById(Integer id);
    AddressResponse add(AddressRequest addressRequest);
    AddressResponse update(AddressRequest addressRequest);
    Result deleteById(Integer id);
}