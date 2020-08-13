package com.appStore.appStore.repository;

import com.appStore.appStore.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface RepositoryCategoryInterf extends JpaRepository<Category, Long >
{
     Category findBynameCateg(String nameCategory);


}