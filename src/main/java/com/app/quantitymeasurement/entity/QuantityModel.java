package com.app.quantitymeasurement.entity;

import com.app.quantitymeasurement.IMeasurable;
import com.app.quantitymeasurement.Quantity;

public class QuantityModel<U extends IMeasurable> {
    private Quantity<U> quantity;

    public QuantityModel(Quantity<U> quantity) {
        this.quantity = quantity;
    }

    public Quantity<U> getQuantity() {
        return quantity;
    }
}
