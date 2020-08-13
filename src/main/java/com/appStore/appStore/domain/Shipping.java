package com.appStore.appStore.domain;

import javax.persistence.*;

@Entity
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shippingId;
    private Long Shop_Cart_Id;
    private String name;
    private String address_1;
    private String address_2;
    private String city;

    public Shipping() {
    }

    public Shipping(Long shippingId, Long shop_Cart_Id, String name, String address_1, String address_2, String city) {
        this.shippingId = shippingId;
        Shop_Cart_Id = shop_Cart_Id;
        this.name = name;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
    }

    public Shipping(String name, String address_1, String address_2, String city) {
        this.name = name;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Long getShop_Cart_Id() {
        return Shop_Cart_Id;
    }

    public void setShop_Cart_Id(Long shop_Cart_Id) {
        Shop_Cart_Id = shop_Cart_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
