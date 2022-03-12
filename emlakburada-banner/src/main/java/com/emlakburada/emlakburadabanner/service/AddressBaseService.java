package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import com.emlakburada.emlakburadabanner.dto.response.AddressResponse;
import com.emlakburada.emlakburadabanner.model.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressBaseService {

    protected AddressResponse convertToAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setProvince(address.getProvince());
        addressResponse.setDistrict(address.getProvince());
        addressResponse.setDetails(address.getDetails());
        return addressResponse;
    }

    protected Address convertToUserAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setProvince(addressRequest.getProvince());
        address.setDistrict(addressRequest.getDistrict());
        address.setDetails(addressRequest.getDetails());
        return address;
    }
}