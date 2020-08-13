package com.appStore.appStore.domain;


import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="ShoppingCarts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantityProduct;
    @Temporal(TemporalType.DATE)
    private Date date;


    public ShoppingCart() {
    }

    public ShoppingCart(Long id,
                        Long quantityProduct,
                        Date date) {
        this.id = id;
        this.quantityProduct = quantityProduct;
        this.date = date;
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
