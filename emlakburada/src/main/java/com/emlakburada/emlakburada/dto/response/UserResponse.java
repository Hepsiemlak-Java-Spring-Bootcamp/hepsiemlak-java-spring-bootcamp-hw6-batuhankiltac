package com.emlakburada.emlakburada.dto.response;

import com.emlakburada.emlakburada.model.enums.UserType;
import lombok.Data;

@Data
public class UserResponse {
    private UserType userType;
    private String name;
    private String email;
    private String photo;
    private String bio;

//    private Set<Advert> favoriteAdverts = new HashSet<>();
//    private List<Advert> publishedAdverts = new ArrayList<>();
//    private List<Message> messageBox = new ArrayList<>();
}