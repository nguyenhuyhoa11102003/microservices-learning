package com.nhh203.productservice.repository;


import com.nhh203.productservice.model.Product;
import com.nhh203.productservice.model.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {
	List<ProductOptionValue> findAllByProduct(Product product);
}

