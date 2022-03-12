package com.emlakburada.emlakburadabanner.service;

import com.emlakburada.emlakburadabanner.dto.request.BannerRequest;
import com.emlakburada.emlakburadabanner.dto.response.BannerResponse;
import com.emlakburada.emlakburadabanner.model.Banner;
import com.emlakburada.emlakburadabanner.repository.BannerRepository;
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
public class BannerServiceTest {

    @InjectMocks
    private BannerServiceImpl bannerService;

    @Mock
    private BannerRepository bannerRepository;

    @Mock
    private BannerBaseService bannerBaseService;

    @Test
    void addBannerTest() {
        BannerRequest bannerRequest = prepareBannerRequest();
        Optional<Banner> banner = Optional.of(prepareBanner());

        Mockito
                .when(bannerRepository.findById(bannerRequest.getId()))
                .thenReturn(banner);

        Mockito
                .when(bannerRepository.save(any()))
                .thenReturn(prepareBanner());

        Mockito
                .when(bannerBaseService.convertToBannerResponse(prepareBanner()))
                .thenReturn(prepareBannerResponse());

        BannerResponse bannerResponse = bannerService.add(bannerRequest);
        assertEquals(1, bannerResponse.getQuantity());
    }

    private BannerRequest prepareBannerRequest() {
        BannerRequest bannerRequest = new BannerRequest();
        bannerRequest.setId(1);
        bannerRequest.setAdvertNo(1);
        bannerRequest.setPhone("12345");
        bannerRequest.setQuantity(1);
        return bannerRequest;
    }

    private Banner prepareBanner() {
        Banner banner = new Banner();
        banner.setId(1);
        banner.setAdvertNo(1);
        banner.setPhone("12345");
        banner.setQuantity(1);
        return banner;
    }

    @Test
    void getAllBannersTest() {
        List<BannerResponse> bannerResponseList = bannerService.getAll();

        Mockito
                .when(bannerRepository.findAll())
                .thenReturn(prepareBannerList());


        Mockito
                .when(bannerBaseService.convertToBannerResponse(prepareBanner()))
                .thenReturn(prepareBannerResponse());

        assertEquals(0, bannerResponseList.size());
        assertThat(bannerResponseList.size());

        for (BannerResponse response : bannerResponseList) {
            assertThat(response.getQuantity());
            assertEquals("12345", response.getPhone());
            assertEquals(1, response.getQuantity());
        }
    }

    private List<Banner> prepareBannerList() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(prepareBanner());
        return bannerList;
    }

    @Test
    void getBannerByIdTest() {
        Mockito
                .when(bannerRepository.findById(1))
                .thenReturn(Optional.of(prepareBanner()));

        Mockito
                .when(bannerBaseService.convertToBannerResponse(prepareBanner()))
                .thenReturn(prepareBannerResponse());

        BannerResponse bannerResponse = bannerService.getBannerById(1);
        assertNotNull(bannerResponse);
        assertEquals("12345", bannerResponse.getPhone());
        assertEquals(1, bannerResponse.getQuantity());
    }

    private BannerResponse prepareBannerResponse() {
        BannerResponse bannerResponse = new BannerResponse();
        bannerResponse.setAdvertNo(1);
        bannerResponse.setPhone("12345");
        bannerResponse.setQuantity(1);
        return bannerResponse;
    }

    @Test
    void updateBannerTest() {
        BannerRequest bannerRequest = prepareBannerRequest();

        Mockito
                .when(bannerRepository.save(any()))
                .thenReturn(prepareBanner());

        Mockito
                .when(bannerRepository.findById(any()))
                .thenReturn(Optional.of(prepareBanner()));

        Mockito
                .when(bannerBaseService.convertToBannerResponse(prepareBanner()))
                .thenReturn(prepareBannerResponse());

        BannerResponse bannerResponse = bannerService.update(bannerRequest);
        assertEquals("12345", bannerResponse.getPhone());
    }

    @Test
    void deleteBannerByIdTest() {
        Mockito
                .doNothing().when(bannerRepository)
                .deleteById(1);

        Mockito
                .when(bannerRepository.findById(1))
                .thenReturn(Optional.of(prepareBanner()));

        bannerService.deleteById(1);
        verify(bannerRepository).deleteById(1);
    }
}