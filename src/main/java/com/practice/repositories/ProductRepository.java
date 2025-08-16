package com.practice.repositories;

import com.practice.entities.Category;
import com.practice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductTitle(String productTitle);

    Optional<List<Product>> findByCategory(Category category);

}
