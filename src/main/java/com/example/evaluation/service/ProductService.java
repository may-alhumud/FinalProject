package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Product;
import com.example.evaluation.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public boolean updateProduct(Product product, Integer id) throws RecordNotFoundException {

        Optional<Product> found = productRepository.findById(id);

        if (found.isEmpty()) {
            throw new RecordNotFoundException("record not found for id " + id);
        }
        var update = found.get();

        update.setName(product.getName());
        update.setModel(product.getModel());

        productRepository.save(update);

        return true;
    }


    public boolean removeProduct(Integer id) {

        Optional<Product> found = productRepository.findById(id);

        if (found.isEmpty()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}