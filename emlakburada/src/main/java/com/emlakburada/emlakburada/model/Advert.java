package com.emlakburada.emlakburada.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer advertNo;
    private String title;
    private Long price;
    private Integer duration;

    @ManyToOne
    private User user;


 //   private RealEstate realEstate;
//    private List<String> pictureList = new ArrayList<>();
//    private Boolean isPromotion;
//    private Boolean isChecked;
//    private Date createdDate;
//    private Boolean isActive;
}