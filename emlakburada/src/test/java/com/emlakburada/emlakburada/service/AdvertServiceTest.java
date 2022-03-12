package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.client.BannerClient;
import com.emlakburada.emlakburada.client.response.BannerResponse;
import com.emlakburada.emlakburada.dto.request.AdvertRequest;
import com.emlakburada.emlakburada.dto.response.AdvertResponse;
import com.emlakburada.emlakburada.model.Advert;
import com.emlakburada.emlakburada.model.User;
import com.emlakburada.emlakburada.model.enums.UserType;
import com.emlakburada.emlakburada.queue.QueueService;
import com.emlakburada.emlakburada.repository.AdvertRepository;
import com.emlakburada.emlakburada.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AdvertServiceTest {

    @InjectMocks
    private AdvertServiceImpl advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QueueService queueService;

    @Mock
    private BannerClient bannerClient;

    @Mock
    private AdvertBaseService advertBaseService;

    @Test
    void addAdvertWithoutUserTest() {
        assertThrows(Exception.class, (Executable) advertService.add(prepareAdvertRequest()));
    }

    private AdvertResponse prepareAdvertResponse() {
        AdvertResponse response = new AdvertResponse();
        response.setUser(prepareUser());
        response.setTitle("test-title");
        response.setPrice(700000L);
        return response;
    }

    private AdvertRequest prepareAdvertRequest() {
        AdvertRequest advertRequest = new AdvertRequest();
        advertRequest.setUserId(1);
        advertRequest.setTitle("test-title");
        advertRequest.setDuration(30);
        advertRequest.setPrice(700000L);
        return advertRequest;
    }

    @Test
    void addAdvertWithUserTest() {
        AdvertRequest advertRequest = prepareAdvertRequest();
        User user = prepareUser();

        Mockito
                .when(userRepository.findById(advertRequest.getUserId()))
                .thenReturn(Optional.of(user));

        Mockito
                .when(advertRepository.save(any()))
                .thenReturn(prepareAdvert("test-title"));

        Mockito
                .when(bannerClient.saveBanner(any()))
                .thenReturn(new ResponseEntity<>(new BannerResponse(), HttpStatus.OK));

        Mockito
                .when(advertBaseService.convertToAdvertResponse(prepareAdvert("test-title")))
                .thenReturn(prepareAdvertResponse());

        Mockito
                .when(advertBaseService.convertToAdvert(prepareAdvertRequest(), Optional.of(prepareUser())))
                .thenReturn(prepareAdvert("test-title"));

        AdvertResponse advertResponse = advertService.add(advertRequest);
        assertEquals("test-title", advertResponse.getTitle());
        verify(queueService).sendMessage(any());
        verify(bannerClient).saveBanner(any());
    }

    @Test
    void addAdvertWithUserTest1() {
        AdvertRequest advertRequest = prepareAdvertRequest();
        Optional<User> user = Optional.of(prepareUser());
        Advert advert = prepareAdvert("");

        Mockito
                .when(userRepository.findById(advertRequest.getUserId()))
                .thenReturn(user);

        Mockito
                .when(advertRepository.save(any()))
                .thenReturn(advert);

        Mockito
                .when(bannerClient.saveBanner(any()))
                .thenReturn(new ResponseEntity<>(new BannerResponse(), HttpStatus.OK));

        Mockito
                .when(advertBaseService.convertToAdvertResponse(advert))
                .thenReturn(prepareAdvertResponse());

        Mockito
                .when(advertBaseService.convertToAdvert(prepareAdvertRequest(), Optional.of(prepareUser())))
                .thenReturn(prepareAdvert("test-title"));

        AdvertResponse advertResponse = advertService.add(advertRequest);
        assertEquals("test-title", advertResponse.getTitle());
        verify(queueService).sendMessage(any());
        verify(bannerClient).saveBanner(any());
    }

    private Advert prepareAdvert(String title) {
        Advert advert = new Advert();
        advert.setId(1);
        advert.setAdvertNo(0);
        advert.setTitle(title);
        advert.setPrice(700000L);
        return advert;
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1);
        user.setUserType(UserType.INDIVIDUAL);
        user.setName("Batuhan");
        user.setEmail("batuhan@gmail.com");
        return user;
    }

    @Test
    void getAllAdvertsTest() {
        Mockito
                .when(advertRepository.findAll())
                .thenReturn(prepareAdvertList());

        Mockito
                .when(advertBaseService.convertToAdvertResponse(prepareAdvert("test-title")))
                .thenReturn(prepareAdvertResponse());

        Mockito
                .when(advertBaseService.convertToAdvert(prepareAdvertRequest(), Optional.of(prepareUser())))
                .thenReturn(prepareAdvert("test-title"));

        List<AdvertResponse> responseList = advertService.getAll();

        assertNotEquals(0, responseList.size());
        assertThat(responseList.size()).isNotZero();

        for (AdvertResponse response : responseList) {
            assertThat(response.getAdvertNo());
            assertEquals("test-title", response.getTitle());
            assertEquals(700000L, response.getPrice());
        }
    }

    private List<Advert> prepareAdvertList() {
        List<Advert> adverts = new ArrayList<>();
        adverts.add(prepareAdvert("test-title"));
        return adverts;
    }

    @Test
    void getAdvertByAdvertIdTest() {
        Mockito
                .when(advertRepository.findById(0))
                .thenReturn(Optional.of(prepareAdvert("test-title")));

        Mockito
                .when(advertBaseService.convertToAdvertResponse(prepareAdvert("test-title")))
                .thenReturn(prepareAdvertResponse());

        Mockito
                .when(advertBaseService.convertToAdvert(prepareAdvertRequest(), Optional.of(prepareUser())))
                .thenReturn(prepareAdvert("test-title"));

        AdvertResponse advertResponse = advertService.getAdvertById(0);
        assertNotNull(advertResponse);
        assertEquals("test-title", advertResponse.getTitle());
        assertNotNull(advertResponse.getUser());
    }

    @Test
    void updateAdvertTest() {
        AdvertRequest advertRequest = prepareAdvertRequest();

        Mockito
                .when(advertRepository.save(any()))
                .thenReturn(prepareAdvert("test-title"));

        Mockito
                .when(advertRepository.findById(any()))
                .thenReturn(Optional.of(prepareAdvert("test-title")));

        Mockito
                .when(advertBaseService.convertToAdvertResponse(prepareAdvert("test-title")))
                .thenReturn(prepareAdvertResponse());

        Mockito
                .when(advertBaseService.convertToAdvert(prepareAdvertRequest(), Optional.of(prepareUser())))
                .thenReturn(prepareAdvert("test-title"));

        AdvertResponse advertResponse = advertService.update(advertRequest);
        assertNotSame("test-title2", advertResponse.getTitle());
    }

    @Test
    void deleteAdvertByIdTest() {
        Mockito
                .doNothing().when(advertRepository)
                .deleteById(1);

        Mockito
                .when(advertRepository.findById(1))
                .thenReturn(Optional.of(prepareAdvert("test-title")));

        advertService.deleteById(1);
        verify(advertRepository).deleteById(1);
    }
}