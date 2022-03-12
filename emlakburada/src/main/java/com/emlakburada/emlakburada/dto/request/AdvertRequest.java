package com.emlakburada.emlakburada.dto.request;

import lombok.Data;

@Data
public class AdvertRequest {
    private Integer id;
    private Integer userId;
    private Integer advertNo;
    private String title;
    private Long price;
    private Integer duration;
}