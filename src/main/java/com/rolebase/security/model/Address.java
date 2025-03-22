package com.rolebase.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;




}
