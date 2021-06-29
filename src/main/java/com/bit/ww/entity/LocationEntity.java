package com.bit.ww.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "location")
@NoArgsConstructor
public class LocationEntity {
    @Id
    @Column
    private String code;

    @Column
    private String city;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private double latitude;

    @Column
    private double longitude;

}
