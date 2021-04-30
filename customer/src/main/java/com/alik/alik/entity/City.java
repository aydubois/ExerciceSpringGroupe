package com.alik.alik.entity;

public class City {
    public String nom;

    public City() {

    }
    public City(String name) {
        this.nom = name;
    }

    public String getName() {
        return nom;
    }

    public void setName(String name) {
        this.nom = name;
    }
}
