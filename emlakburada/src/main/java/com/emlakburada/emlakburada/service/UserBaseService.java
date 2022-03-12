package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.dto.request.UserRequest;
import com.emlakburada.emlakburada.dto.response.UserResponse;
import com.emlakburada.emlakburada.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserBaseService {

    protected User convertToUserEntity(UserRequest userRequest) {
        User user = new User();
        user.setUserType(userRequest.getUserType());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setBio(userRequest.getBio());
        return user;
    }

    protected UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserType(user.getUserType());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBio(user.getBio());
        return userResponse;
    }
}