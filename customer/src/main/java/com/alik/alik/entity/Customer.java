package com.alik.alik.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@AllArgsConstructor
@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String zipCode;

    public Customer() {

    }

    public Customer(String name) {
        this.name = name;
    }
    public Customer(String name, String zipCode, String city){
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
    }
}
