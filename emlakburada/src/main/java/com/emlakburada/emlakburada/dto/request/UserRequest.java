package com.emlakburada.emlakburada.dto.request;

import com.emlakburada.emlakburada.model.enums.UserType;
import lombok.Data;

@Data
public class UserRequest {
    private Integer id;
    private UserType userType;
    private String name;
    private String email;
    private String bio;
}