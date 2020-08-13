package com.appStore.appStore.DO;


import java.util.Date;


public class ClientOrders {

    private Long userId;
    private String username;
    private Long id;
    private Long quantityProduct;
    private Date date;



    public ClientOrders() {
    }

    public ClientOrders(Long userId,
                        String username,
                        Long id,
                        Long quantityProduct,
                        Date date) {
        this.userId = userId;
        this.username = username;
        this.id = id;
        this.quantityProduct = quantityProduct;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Long quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
