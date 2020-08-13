package com.appStore.appStore.DO;

import com.appStore.appStore.domain.ShoppingCart;
import com.appStore.appStore.domain.ShoppingCartProducts;

import java.util.List;

public class ClientShoppingCart {

    private ShoppingCart shoppingCart;
    private List<ShoppingCartProducts> shoppingCartProducts;

    public ClientShoppingCart() {
    }

    public ClientShoppingCart(ShoppingCart shoppingCart, List<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCart = shoppingCart;
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<ShoppingCartProducts> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(List<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }
}
