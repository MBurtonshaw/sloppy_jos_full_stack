package com.techelevator.model;

import java.io.PrintWriter;

public class Customer extends Food{

private int customer_id;
private String first_name;
private String last_name;
private String street_address;
private String city;
private int phone_number;
private String email_address;
private boolean is_registered;
private String credit_card;
private String customer_fav;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public String getCustomer_fav() {
        return customer_fav;
    }

    public void setCustomer_fav(String customer_fav) {
        this.customer_fav = customer_fav;
    }
}
