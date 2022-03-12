package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.dto.request.UserRequest;
import com.emlakburada.emlakburada.dto.response.UserResponse;
import com.emlakburada.emlakburada.model.User;
import com.emlakburada.emlakburada.model.enums.UserType;
import com.emlakburada.emlakburada.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserBaseService userBaseService;

    @Test
    void addUserTest() {
        UserRequest userRequest = prepareUserRequest();
        Optional<User> user = Optional.of(prepareUser());

        Mockito
                .when(userRepository.findById(userRequest.getId()))
                .thenReturn(user);

        Mockito
                .when(userRepository.save(any()))
                .thenReturn(prepareUser());

        Mockito
                .when(userBaseService.convertToUserResponse(prepareUser()))
                .thenReturn(prepareUserResponse());

        UserResponse userResponse = userService.add(userRequest);
        assertEquals("test-name", userResponse.getName());
    }

    private UserRequest prepareUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1);
        userRequest.setUserType(UserType.INDIVIDUAL);
        userRequest.setName("test-name");
        userRequest.setEmail("batuhan@gmail.com");
        return userRequest;
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
    void getAllUsersTest() {
        List<UserResponse> responseList = userService.getAll();

        Mockito
                .when(userRepository.findAll())
                .thenReturn(prepareUserList());


        Mockito
                .when(userBaseService.convertToUserResponse(prepareUser()))
                .thenReturn(prepareUserResponse());

        assertEquals(0, responseList.size());
        assertThat(responseList.size());

        for (UserResponse response : responseList) {
            assertThat(response.getName());
            assertEquals("test-name", response.getName());
            assertEquals("batuhan@gmail.com", response.getEmail());
        }
    }

    private List<User> prepareUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(prepareUser());
        return userList;
    }

    @Test
    void getUserByIdTest() {
        Mockito
                .when(userRepository.findById(1))
                .thenReturn(Optional.of(prepareUser()));

        Mockito
                .when(userBaseService.convertToUserResponse(prepareUser()))
                .thenReturn(prepareUserResponse());

        UserResponse userResponse = userService.getUserById(1);
        assertNotNull(userResponse);
        assertEquals("test-name", userResponse.getName());
        assertEquals("batuhan@gmail.com", userResponse.getEmail());
    }

    private UserResponse prepareUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserType(UserType.INDIVIDUAL);
        userResponse.setName("test-name");
        userResponse.setEmail("batuhan@gmail.com");
        userResponse.setBio("1990");
        return userResponse;
    }

    @Test
    void updateUserTest() {
        UserRequest userRequest = prepareUserRequest();

        Mockito
                .when(userRepository.save(any()))
                .thenReturn(prepareUser());

        Mockito
                .when(userRepository.findById(any()))
                .thenReturn(Optional.of(prepareUser()));

        Mockito
                .when(userBaseService.convertToUserResponse(prepareUser()))
                .thenReturn(prepareUserResponse());

        UserResponse userResponse = userService.update(userRequest);
        assertEquals("test-name", userResponse.getName());
    }

    @Test
    void deleteUserByIdTest() {
        Mockito
                .doNothing().when(userRepository)
                .deleteById(1);

        Mockito
                .when(userRepository.findById(1))
                .thenReturn(Optional.of(prepareUser()));

        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }
}