package com.example.agc.backend;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LOCAL")
public class LocalSupplier extends Supplier {
    private String region;

    public LocalSupplier() {
        super();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
