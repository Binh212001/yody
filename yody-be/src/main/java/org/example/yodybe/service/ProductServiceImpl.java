package org.example.yodybe.service;

import org.example.yodybe.entity.*;
import org.example.yodybe.form.ProductForm;
import org.example.yodybe.repositoties.*;
import org.example.yodybe.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ProductColorRepository productColorRepository;

    @Override
    public BaseResponse save(String name, String description, Double price, Long categoryId, List<Long> colorIds, List<Long> sizeIds, List<MultipartFile> images) {
        try {
            Product entity = new Product();
            entity.setName(name);
            entity.setDescription(description);
            entity.setPrice(price);

            // Handle categories, colors, and sizes
//            Optional<Category> category = categoryRepository.findById(categoryId);
//            if (category.isEmpty()) {
//                return new BaseResponse("Category is not found", null, 400);
//            }
//            entity.setCategories(category.get());
            List<Color> colors = colorRepository.findAllById(colorIds);
            if (colors.isEmpty()) {
                return new BaseResponse("Color(s) not found", null, 400);
            }
//            entity.setColors(colors);
//            List<Size> sizes = sizeRepository.findAllById(colorIds);
//            if (colors.isEmpty()) {
//                return new BaseResponse("Size(s) not found", null, 400);
//            }
//            entity.setSizes(sizes);


            // Handle image file saving (implementation depends on your storage solution)
            // Save images to your storage and associate them with the product
            Product savedProduct = productRepository.save(entity);
            for (Color color : colors){
            ProductColor productColor = new ProductColor();
            productColor.setProduct(savedProduct);
            productColor.setColor(color);
            productColorRepository.save(productColor);
            }


            return new BaseResponse("Product saved successfully", savedProduct, 200);
        } catch (Exception e) {
            return new BaseResponse("Error saving product", null, 500);
        }
    }
}
