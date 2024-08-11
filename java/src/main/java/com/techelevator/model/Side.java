package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Side extends Food {

    int side_id;

    String side_name;

    double base_price;

    public Side(int side_id, String side_name, double base_price) {
        this.side_id = side_id;
        this.side_name = side_name;
        this.base_price = base_price;

    }

    public Side() {

    }
}
