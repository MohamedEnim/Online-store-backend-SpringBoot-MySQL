package com.appStore.appStore.VO;

public class DBShipping {

    private String name;
    private String address_1;
    private String city;
    private String address_2;


    public DBShipping() {
    }

    public DBShipping(String name, String address_1, String city, String address_2) {
        this.name = name;
        this.address_1 = address_1;
        this.city = city;
        this.address_2 = address_2;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }
}
