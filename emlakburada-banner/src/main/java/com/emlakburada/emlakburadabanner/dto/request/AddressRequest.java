package com.emlakburada.emlakburadabanner.dto.request;

import com.emlakburada.emlakburadabanner.model.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddressRequest extends Address {
    private Integer id;
    private String province;
    private String district;
    private String details;
}