package com.appStore.appStore.domain;

import javax.persistence.*;

@Entity
public class ShoppingCartProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    private Long shop_Cart_Id;

    private String nameProd;

    private Integer price;

    private  Integer quantity;
    @Lob
    private String imgUrl;

    private Integer quantityProduct;

    public ShoppingCartProducts() {
    }

    public ShoppingCartProducts(Long id,
                                Long shop_Cart_Id,
                                String nameProd,
                                Integer price,
                                Integer quantity,
                                String imgUrl,
                                Integer quantityProduct) {
        this.id = id;
        this.shop_Cart_Id = shop_Cart_Id;
        this.nameProd = nameProd;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
        this.quantityProduct = quantityProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop_Cart_Id() {
        return shop_Cart_Id;
    }

    public void setShop_Cart_Id(Long shop_Cart_Id) {
        this.shop_Cart_Id = shop_Cart_Id;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
