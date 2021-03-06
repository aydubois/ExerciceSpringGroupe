package com.order.order.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;
    private int lattitude = 9999;
    private int longitude = 9999;

    @Column
    private Long idCustomer;

    @Column
    private Long idUser;

    public Order() {}
    public Order(String name) {
        this.name = name;
    }

    public Order(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Order(String name, int lattitude, int longitude) {
        this.name = name;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public Order(String name, long idUser, long idCustomer) {
        this.name = name;
        this.idUser = idUser;
        this.idCustomer = idCustomer;
    }

}
