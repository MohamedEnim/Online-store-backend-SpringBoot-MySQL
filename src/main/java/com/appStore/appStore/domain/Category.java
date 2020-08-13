package com.appStore.appStore.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameCateg;
    private Integer numProduct;
    @OneToMany(targetEntity = Product.class, cascade = {CascadeType.ALL})
    @JoinColumn(name="c_product", referencedColumnName = "id")
    private List<Product> products = new ArrayList<Product>();

    public Category() {
    }

    public Category(String nameCateg, Integer numProduct, List<Product> products) {
        this.nameCateg = nameCateg;
        this.numProduct = numProduct;
        this.products = products;
    }

    public Category(String nameCateg, Integer numProduct) {
        this.nameCateg = nameCateg;
        this.numProduct = numProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCateg() {
        return nameCateg;
    }

    public void setNameCateg(String nameCateg) {
        this.nameCateg = nameCateg;
    }

    public Integer getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(Integer numProduct) {
        this.numProduct = numProduct;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}