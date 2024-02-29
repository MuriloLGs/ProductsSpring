package com.example.product.implementation;


import com.example.product.DTO.ProductRecordDTO;
import com.example.product.models.ProductModel;
import com.example.product.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public ProductModel saveProduct(ProductRecordDTO productRecordDTO) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();

    }

    public Optional<ProductModel> getOneProduct(UUID id) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        return productRepository.findById(id);
    }

    public ProductModel updateProduct(UUID id, ProductRecordDTO productRecordDTO) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            var productModel = optionalProduct.get();
            BeanUtils.copyProperties(productRecordDTO, productModel);
            return productRepository.save(productModel);
        }
        return null;
    }

    public boolean deleteProduct(UUID id) {

        Optional<ProductModel> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;

    }
}


