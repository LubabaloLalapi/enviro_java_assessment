package com.enviro.assessment.grad001.lubabalolalapi.service;

import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productService {
    @Autowired
    private productRepository productRepository;

    public product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
