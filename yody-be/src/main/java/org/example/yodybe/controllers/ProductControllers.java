package org.example.yodybe.controllers;

import org.example.yodybe.form.ProductForm;
import org.example.yodybe.service.ProductService;
import org.example.yodybe.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = {})
@RequestMapping("/api/products")
public class ProductControllers {
    @Autowired
    ProductService productService;
    // TODO: Implement CRUD operations for Product entity
    // GET /products
    // POST /products
    @PostMapping
    public ResponseEntity<BaseResponse> createProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("categories") Long categoryId,
            @RequestParam("colors") List<Long> colorIds,
            @RequestParam("sizes") List<Long> sizeIds,
            @RequestParam("images") List<MultipartFile> images) {


        BaseResponse savedProduct= productService.save(name, description, price, categoryId,colorIds,sizeIds, images);

        return ResponseEntity.ok(savedProduct);
    }

    // GET /products/{id}
    // PUT /products/{id}
    // DELETE /products/{id}
    // GET /products?category={categoryId}
    // GET /products?color={colorId}
    // GET /products?size={sizeId}
    // GET /products?collection={collectionId}
    // GET /products?priceRange={minPrice}-{maxPrice}
    // GET /products?search={searchTerm}

}
