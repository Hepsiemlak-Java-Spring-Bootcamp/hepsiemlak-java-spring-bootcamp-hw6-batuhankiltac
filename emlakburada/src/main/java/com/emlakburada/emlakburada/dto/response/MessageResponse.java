package com.emlakburada.emlakburada.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {
    private String title;
    private String description;
    private Date sentDate;
    private Date readDate;
    private Boolean isSeen;
    //private User sender;
    //private User receiver;
}