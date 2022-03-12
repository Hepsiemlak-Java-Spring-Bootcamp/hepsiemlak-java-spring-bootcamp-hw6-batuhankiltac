package com.emlakburada.emlakburadabanner.dto.response;

import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import lombok.Data;

@Data
public class BannerResponse {
    private Integer advertNo;
    private String phone;
    private Integer quantity;
    private AddressRequest addressRequest;
}