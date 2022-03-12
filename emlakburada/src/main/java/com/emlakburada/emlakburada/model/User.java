package com.emlakburada.emlakburada.model;

import com.emlakburada.emlakburada.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String name;
    private String email;
    private String photo;
    private String bio;

    @OneToMany
    private List<Message> messages;

//	private Set<Advert> favoriteAdverts = new HashSet<>();
//	private List<Advert> publishedAdverts = new ArrayList<>();
//	private List<Message> messageBox = new ArrayList<>();
}