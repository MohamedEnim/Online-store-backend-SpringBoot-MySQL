package com.appStore.appStore.repository;

import com.appStore.appStore.domain.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryShippingInterf extends JpaRepository<Shipping, Long> {
}
