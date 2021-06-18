package com.bit.ww.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_location")
@NoArgsConstructor
public class UserLocationEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column
    private String userid;

    @Column
    private String address2;

    @Column
    private double latitude;

    @Column
    private double longitude;

}
