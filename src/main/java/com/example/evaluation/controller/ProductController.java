package com.example.evaluation.controller;


import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Product;
import com.example.evaluation.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    Logger logger= LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        logger.info("get all Product");
        List<Product> product=productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/add-product")
    public ResponseEntity<API> addProduct(@RequestBody @Valid Product product){
        logger.info("add Product");
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Record added !",201));
    }


    @PutMapping("/update-product/{id}")
    public ResponseEntity<API> UpdateProduct(@RequestBody @Valid Product product,@PathVariable @Valid Integer id) throws RecordNotFoundException {
         logger.info("update Product");
        productService.updateProduct(product,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new API("Record updated",200));
    }


    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<API> removeProduct(@PathVariable @Valid Integer id){
         logger.info("add book");
        var removed = productService.removeProduct(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
