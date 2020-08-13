package com.appStore.appStore.domain;



import javax.persistence.*;



@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    private String nameProd;

    private Integer price;

    private  Integer quantity;
   @Lob
    private String imgUrl;


    public Product() {
    }

    public Product(Long id, String nameProd, Integer price, Integer quantity, String imgUrl) {
        this.id = id;
        this.nameProd = nameProd;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
