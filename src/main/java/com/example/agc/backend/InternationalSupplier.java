package com.example.agc.backend;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("INTERNATIONAL")
public class InternationalSupplier extends Supplier {
    private String country;

    public InternationalSupplier() {
        super();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
