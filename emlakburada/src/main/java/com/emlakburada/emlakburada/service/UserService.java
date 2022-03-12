package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.UserRequest;
import com.emlakburada.emlakburada.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getUserById(Integer id);
    UserResponse add(UserRequest userRequest);
    UserResponse update(UserRequest userRequest);
    Result deleteById(Integer id);
}