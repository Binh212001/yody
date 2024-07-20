package org.example.yodybe.controllers;

import lombok.Getter;
import org.example.yodybe.form.ProductForm;
import org.example.yodybe.service.ProductService;
import org.example.yodybe.utils.BaseResponse;
import org.example.yodybe.utils.PaginationResponse;
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
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "collection", required = false) Long collectionId,
            @RequestParam(value = "priceRange", required = false) String priceRange,
            @RequestParam(value = "color", required = false) Long colorId,
            @RequestParam(value = "size", required = false) Long sizeId,
            @RequestParam(value = "category", required = false) String categoryId
    ) {
        PaginationResponse paginationResponse= productService.getProductList(page,size,collectionId,colorId,sizeId,priceRange, categoryId);
        return ResponseEntity.ok(paginationResponse);
    }

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
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable Long id) {
        BaseResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<BaseResponse> getProductsByFilters(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long colorId,
            @RequestParam(required = false) Long sizeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BaseResponse response = productService.getProductsByFilters(categoryId, colorId, sizeId, page, size);
        return ResponseEntity.ok(response);
    }
    // PUT /products/{id}

    @PutMapping
    public ResponseEntity<BaseResponse> updateProduct(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("categories") Long categoryId,
            @RequestParam("colors") List<Long> colorIds,
            @RequestParam("sizes") List<Long> sizeIds,
            @RequestParam("images") List<MultipartFile> images) {
        BaseResponse savedProduct= productService.update(id,name, description, price, categoryId,colorIds,sizeIds, images);
        return ResponseEntity.ok(savedProduct);
    }
    // DELETE /products/{id}

    // GET /products/collection?collection={collectionId}
    @GetMapping("/collection")
    public ResponseEntity<PaginationResponse> getProductsByCollection(
            @RequestParam(value = "collectionId", required = false) String collectionId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PaginationResponse paginationResponse = productService.getProductsByCollection(collectionId, page, size);
        return ResponseEntity.ok(paginationResponse);
    }
    // GET /products?priceRange={minPrice}-{maxPrice}
    @GetMapping("/priceRange")
    public ResponseEntity<PaginationResponse> getProductsByPriceRange(
            @RequestParam(value = "minPrice", required = false) String minPrice,
            @RequestParam(value = "maxPrice", required = false) String maxPrice,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PaginationResponse paginationResponse = productService.getProductsByPriceRange(minPrice, maxPrice, page, size);
        return ResponseEntity.ok(paginationResponse);
    }
    // GET /products/search?searchTerm=
    @GetMapping("/search")
    public ResponseEntity<PaginationResponse> getProductsBySearchTerm(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        PaginationResponse paginationResponse = productService.getProductsBySearchTerm(searchTerm, page, size);
        return ResponseEntity.ok(paginationResponse);
    }
}
