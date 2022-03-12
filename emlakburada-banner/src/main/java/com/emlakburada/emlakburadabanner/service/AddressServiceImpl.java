package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.core.Result;
import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import com.emlakburada.emlakburadabanner.dto.response.AddressResponse;
import com.emlakburada.emlakburadabanner.model.Address;
import com.emlakburada.emlakburadabanner.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressBaseService addressBaseService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressBaseService addressBaseService) {
        this.addressRepository = addressRepository;
        this.addressBaseService = addressBaseService;
    }

    @Override
    public List<AddressResponse> getAll() {
        List<AddressResponse> addressList = new ArrayList<>();
        for (Address address : addressRepository.findAll()) {
            addressList.add(addressBaseService.convertToAddressResponse(address));
        }
        return addressList;
    }

    @Override
    public AddressResponse getAddressById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        return addressBaseService.convertToAddressResponse(address.get());
    }

    @Override
    public AddressResponse add(AddressRequest addressRequest) {
        Address address = addressBaseService.convertToUserAddress(addressRequest);
        return addressBaseService.convertToAddressResponse(addressRepository.save(address));
    }

    @Override
    public AddressResponse update(AddressRequest addressRequest) {
        getAddressById(addressRequest.getId());
        Address address = addressBaseService.convertToUserAddress(addressRequest);
        return addressBaseService.convertToAddressResponse(addressRepository.save(address));
    }

    @Override
    public Result deleteById(Integer id) {
        getAddressById(id);
        addressRepository.deleteById(id);
        return new Result(true, "Address has been deleted.");
    }
}