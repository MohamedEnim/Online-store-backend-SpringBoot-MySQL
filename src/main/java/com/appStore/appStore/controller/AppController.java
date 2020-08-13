package com.appStore.appStore.controller;

import com.appStore.appStore.DO.ClientOrders;
import com.appStore.appStore.DO.ClientShoppingCart;
import com.appStore.appStore.VO.DBShipping;
import com.appStore.appStore.VO.ShoppingProducts;
import com.appStore.appStore.domain.*;
import com.appStore.appStore.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RequestMapping("/appstore")
@RestController
public class AppController {
    @Autowired
    public AppService appService;


    @RequestMapping(value ="/category" ,method = RequestMethod.POST)
    Category addCategory(@RequestBody Category category){
        return this.appService.addCategory(category);
    }

    @RequestMapping(value ="/categories" ,method = RequestMethod.GET)
    Iterable<Category> returnCategory(){
        return this.appService.returnCategories();
    }


    @RequestMapping(value ="select/{nameCategory}" ,method = RequestMethod.GET)
    Category selectCategory(@PathVariable("nameCategory") String nameCategory){
        return this.appService.selectCategory(nameCategory);
    }

    @RequestMapping(value ="/product/{indexProd}" ,method = RequestMethod.GET)
     Optional<Product> getProduct(@PathVariable("indexProd") long indexProd) {
        return this.appService.getProduct(indexProd);
    }

   @RequestMapping(value ="/postProduct/{dbCategoryIndex}/{dbProductIndex}" ,method = RequestMethod.POST)
    public Iterable<Category> updateProduct(@PathVariable("dbCategoryIndex") Long dbCategoryIndex,@PathVariable("dbProductIndex") Long dbProductIndex, @RequestBody Product product) {
        return this.appService.updateProduct(dbCategoryIndex, dbProductIndex, product);
    }


    @RequestMapping(value ="/deleteProduct/{dbCategoryIndex}/{dbProductIndex}" ,method = RequestMethod.DELETE)
    public Iterable<Category> deleteProduct(@PathVariable("dbCategoryIndex") Long dbCategoryIndex,@PathVariable("dbProductIndex") Long dbProductIndex) {
        return this.appService.deleteProduct(dbCategoryIndex, dbProductIndex);
    }

    @RequestMapping(value ="/addShoppingCart/{userId}" ,method = RequestMethod.POST)
    public ClientShoppingCart addShoppingCart(@PathVariable("userId") Long userId, @RequestBody ShoppingProducts shoppingProducts){
        return this.appService.addShoppingCart(userId, shoppingProducts);
    }

    @RequestMapping(value ="/addProdToShoppingCart/{userId}/{cartId}" ,method = RequestMethod.POST)
    public ClientShoppingCart addProductToShoppingCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId, @RequestBody ShoppingProducts shoppingProducts) {
        return this.appService.addProductToShoppingCart(userId, cartId, shoppingProducts);
    }

    @RequestMapping(value ="/getShoppingCart/{userId}/{cartId}", method = RequestMethod.GET)
    public ClientShoppingCart getShoppingCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {
        return this.appService.getShoppingCart(userId, cartId);
    }

    @RequestMapping(value ="/deleteShoppingCartProduct/{userId}/{cartId}/{prodId}", method = RequestMethod.DELETE)
    public  ClientShoppingCart removeProdShoppingCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId, @PathVariable("prodId") Long prodId) {
        return this.appService.removeProdShoppingCart(userId, cartId, prodId);
    }

    @RequestMapping(value ="/deleteShoppingCart/{userId}/{cartId}", method = RequestMethod.DELETE)
    public  ClientShoppingCart removeShoppingCart(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId){
        return this.appService.removeShoppingCart(userId, cartId);
    }

    @RequestMapping(value ="/existeCategory/{nameCateg}", method = RequestMethod.POST)
    public Category addNewProdToCategory(@PathVariable("nameCateg") String nameCateg, @RequestBody List<Product> newProducts) {
        return this.appService.addNewProdToCategory(nameCateg, newProducts);
    }

    @RequestMapping(value ="/my/orders/{userId}", method = RequestMethod.GET)
    public  List<ShoppingCart> getMyOrders(@PathVariable("userId") Long userId) {
        return this.appService.getMyOrders(userId);
    }

    @RequestMapping(value ="/my/order/detail/{userId}/{cartId}", method = RequestMethod.GET)
    public  List<ShoppingCartProducts> getMyOrderOrderDetail(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId) {
        return this.appService.getMyOrderDetail(userId, cartId);
    }

    @RequestMapping(value ="/orders", method = RequestMethod.GET)
    public  List<ClientOrders> getOrders() {
        return this.appService.getOrders();
    }

    @RequestMapping(value ="/shipping/{userId}/{cartId}" ,method = RequestMethod.POST)
    public Shipping addshipping(@PathVariable("userId") Long userId, @PathVariable("cartId") Long cartId, @RequestBody DBShipping shipping) {
        return this.appService.addshipping(userId, cartId, shipping);
    }



}
