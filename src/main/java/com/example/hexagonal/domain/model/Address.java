package com.example.hexagonal.domain.model;

import jakarta.persistence.*;
@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address() {
    }

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    
}
