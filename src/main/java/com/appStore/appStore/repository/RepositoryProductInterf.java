package com.appStore.appStore.repository;


import com.appStore.appStore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryProductInterf extends JpaRepository<Product, Long > {

}
