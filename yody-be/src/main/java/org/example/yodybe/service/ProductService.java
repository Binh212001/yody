package org.example.yodybe.service;

import org.example.yodybe.form.ProductForm;
import org.example.yodybe.utils.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    BaseResponse save(String name, String description, Double price, Long categoryId, List<Long> colorIds, List<Long> sizeIds, List<MultipartFile> images);
}
