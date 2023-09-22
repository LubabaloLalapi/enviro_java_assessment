package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private com.enviro.assessment.grad001.lubabalolalapi.service.productService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<product> getProduct(@PathVariable Long productId) {
        product product = productService.getProduct(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
