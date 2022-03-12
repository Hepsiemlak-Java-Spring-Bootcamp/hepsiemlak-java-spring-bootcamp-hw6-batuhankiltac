package com.emlakburada.emlakburada.model;

import com.emlakburada.emlakburada.model.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealEstate {
    private Address address;
    private RealEstateType realEstateType;
    private String rooms;
    private Integer size;
    private Integer floor;
}
