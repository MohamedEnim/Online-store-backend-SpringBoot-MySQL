package com.appStore.appStore.services;


import com.appStore.appStore.DO.ClientOrders;
import com.appStore.appStore.DO.ClientShoppingCart;
import com.appStore.appStore.VO.DBShipping;
import com.appStore.appStore.VO.ShoppingProducts;
import com.appStore.appStore.authentication.authDomain.AuthenticationUser;
import com.appStore.appStore.domain.*;

import java.util.List;
import java.util.Optional;


public interface AppServiceInterf {
     Category addCategory(Category category);
     Iterable<Category> returnCategories();
     Category selectCategory(String nameCategory);
     Optional<Product>  getProduct(long indexProd);
     Iterable<Category> updateProduct(Long idCateg, Long idProd, Product product);
     Iterable<Category> deleteProduct(Long idCateg, Long idProd);
     ClientShoppingCart addShoppingCart(Long userId, ShoppingProducts shoppingProducts);
     ClientShoppingCart addProductToShoppingCart(Long userId, Long cartId, ShoppingProducts shoppingProducts);
     ClientShoppingCart getShoppingCart(Long userId, Long cartId);
     ClientShoppingCart removeProdShoppingCart(Long userId, Long cartId, Long prodId);
     ClientShoppingCart removeShoppingCart(Long userId, Long cartId);
     Category addNewProdToCategory(String nameCateg, List<Product> newProducts);
     List<ShoppingCart> getMyOrders(Long userId);
     List<ShoppingCartProducts> getMyOrderDetail(Long userId, Long cartId);
     List<ClientOrders> getOrders();
     Shipping addshipping(Long userId, Long cartId, DBShipping shipping);


}
