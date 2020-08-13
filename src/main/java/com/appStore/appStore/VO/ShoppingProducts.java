package com.appStore.appStore.VO;

import com.appStore.appStore.domain.Product;

public class ShoppingProducts {

    private Product products;
    private Integer quantityProduct;

    public ShoppingProducts() {
    }

    public ShoppingProducts(Product products, Integer quantityProduct) {
        this.products = products;
        this.quantityProduct = quantityProduct;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }
}
