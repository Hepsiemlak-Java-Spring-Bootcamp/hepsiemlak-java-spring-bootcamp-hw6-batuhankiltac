package com.emlakburada.emlakburada.dto.response;

import com.emlakburada.emlakburada.model.User;
import lombok.Data;

@Data
public class AdvertResponse {
    private Integer advertNo;
    private String title;
    private Long price;
    private User user;

 //   private RealEstate realEstate;
//    private List<String> pictureList = new ArrayList<>();
//    private Integer duration;
//    private Boolean isPromotion;
//    private Boolean isChecked;
//    private Date createdDate;
//    private Boolean isActive;
}