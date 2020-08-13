package com.appStore.appStore.repository;

import com.appStore.appStore.domain.ShoppingCartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryShoppingCartProductsInterf extends JpaRepository<ShoppingCartProducts, Long> {

}
