package com.appStore.appStore.repository;

import com.appStore.appStore.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryShoppingCartInterf extends JpaRepository<ShoppingCart, Long > {

}
