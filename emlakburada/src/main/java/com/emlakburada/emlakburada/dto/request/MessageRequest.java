package com.emlakburada.emlakburada.dto.request;

import lombok.Data;

@Data
public class MessageRequest {
    private Integer id;
    private String title;
    private String description;
    //private User sender;
    //private User receiver;
}