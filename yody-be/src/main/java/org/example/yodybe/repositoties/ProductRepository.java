package org.example.yodybe.repositoties;

import org.example.yodybe.entity.Category;
import org.example.yodybe.entity.Color;
import org.example.yodybe.entity.Product;
import org.example.yodybe.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product  p  ")
    Page<Product> getProductList(Pageable pageable);
    Page<Product> findByColorsOrCategories(Color color, Category category, Pageable pageable);

    Page<Product> findByColorsOrCategoriesOrSizes(Color color , Category category, Size size, Pageable pageable);

    Page<Product> findByColorsOrSizes(Color color, Size size, Pageable pageable);

    Page<Product> findByCategoriesOrSizes(Category category, Size size, Pageable pageable);

    Page<Product> findByColors(Color color, Pageable pageable);

    Page<Product> findBySizes(Size size, Pageable pageable);

    Page<Product> findByCategories(Category category, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchTerm%")
    Page<Product> searchProduct(Pageable pageable, String searchTerm);
    Page<Product> findByCollection(String collectionId, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> getByPrice(Pageable pageable, String minPrice, String maxPrice);
}