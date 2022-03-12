package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.dto.request.AddressRequest;
import com.emlakburada.emlakburadabanner.dto.response.AddressResponse;
import com.emlakburada.emlakburadabanner.model.Address;
import com.emlakburada.emlakburadabanner.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressBaseService addressBaseService;

    @Test
    void addAddressTest() {
        AddressRequest addressRequest = prepareAddressRequest();
        Optional<Address> address = Optional.of(prepareAddress());

        Mockito
                .when(addressRepository.findById(addressRequest.getId()))
                .thenReturn(address);

        Mockito
                .when(addressRepository.save(any()))
                .thenReturn(prepareAddress());

        Mockito
                .when(addressBaseService.convertToAddressResponse(prepareAddress()))
                .thenReturn(prepareAddressResponse());

        AddressResponse addressResponse = addressService.add(addressRequest);
        assertEquals(1, addressResponse.getId());
    }

    private AddressRequest prepareAddressRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setId(1);
        addressRequest.setProvince("test-province");
        addressRequest.setDistrict("test-district");
        addressRequest.setDetails("test-details");
        return addressRequest;
    }

    private Address prepareAddress() {
        Address address = new Address();
        address.setId(1);
        address.setProvince("test-province");
        address.setDistrict("test-district");
        address.setDetails("test-details");
        return address;
    }

    @Test
    void getAllAddressesTest() {
        List<AddressResponse> addressResponseList = addressService.getAll();

        Mockito
                .when(addressRepository.findAll())
                .thenReturn(prepareAddressList());

        Mockito
                .when(addressBaseService.convertToAddressResponse(prepareAddress()))
                .thenReturn(prepareAddressResponse());

        assertEquals(0, addressResponseList.size());
        assertThat(addressResponseList.size());

        for (AddressResponse response : addressResponseList) {
            assertThat(response.getId());
            assertEquals("test-province", response.getProvince());
            assertEquals("test-district", response.getDistrict());
        }
    }

    private List<Address> prepareAddressList() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(prepareAddress());
        return addressList;
    }

    @Test
    void getAddressByIdTest() {
        Mockito
                .when(addressRepository.findById(1))
                .thenReturn(Optional.of(prepareAddress()));

        Mockito
                .when(addressBaseService.convertToAddressResponse(prepareAddress()))
                .thenReturn(prepareAddressResponse());

        AddressResponse addressResponse = addressService.getAddressById(1);
        assertNotNull(addressResponse);
        assertEquals("test-province", addressResponse.getProvince());
        assertEquals("test-district", addressResponse.getDistrict());
    }

    private AddressResponse prepareAddressResponse() {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(1);
        addressResponse.setProvince("test-province");
        addressResponse.setDistrict("test-district");
        return addressResponse;
    }

    @Test
    void updateAddressTest() {
        AddressRequest addressRequest = prepareAddressRequest();

        Mockito
                .when(addressRepository.save(any()))
                .thenReturn(prepareAddress());

        Mockito
                .when(addressRepository.findById(any()))
                .thenReturn(Optional.of(prepareAddress()));

        Mockito
                .when(addressBaseService.convertToAddressResponse(prepareAddress()))
                .thenReturn(prepareAddressResponse());

        AddressResponse addressResponse = addressService.update(addressRequest);
        assertEquals("test-province", addressResponse.getProvince());
    }

    @Test
    void deleteAddressByIdTest() {
        Mockito
                .doNothing().when(addressRepository)
                .deleteById(1);

        Mockito
                .when(addressRepository.findById(1))
                .thenReturn(Optional.of(prepareAddress()));

        addressService.deleteById(1);
        verify(addressRepository).deleteById(1);
    }
}