package com.emlakburada.emlakburadabanner.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
    private Integer id;
    private String province;
    private String district;
    private String details;
}