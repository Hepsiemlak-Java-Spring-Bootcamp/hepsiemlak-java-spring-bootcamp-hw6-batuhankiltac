package com.emlakburada.emlakburada.service;

import com.emlakburada.emlakburada.core.Result;
import com.emlakburada.emlakburada.dto.request.UserRequest;
import com.emlakburada.emlakburada.dto.response.UserResponse;
import com.emlakburada.emlakburada.model.User;
import com.emlakburada.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserBaseService userBaseService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserBaseService userBaseService) {
        this.userRepository = userRepository;
        this.userBaseService = userBaseService;
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userList.add(userBaseService.convertToUserResponse(user));
        }
        return userList;
    }

    @Override
    public UserResponse getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return userBaseService.convertToUserResponse(user.get());
    }

    @Override
    public UserResponse add(UserRequest userRequest) {
        User user = userBaseService.convertToUserEntity(userRequest);
        return userBaseService.convertToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        getUserById(userRequest.getId());
        User user = userBaseService.convertToUserEntity(userRequest);
        return userBaseService.convertToUserResponse(userRepository.save(user));
    }

    @Override
    public Result deleteById(Integer id) {
        getUserById(id);
        userRepository.deleteById(id);
        return new Result(true, "User has been deleted.");
    }
}