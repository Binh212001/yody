package org.example.yodybe.service;

import org.example.yodybe.dto.SizeDto;
import org.example.yodybe.entity.Category;
import org.example.yodybe.form.CategoryForm;
import org.example.yodybe.repositoties.CategoryRepository;
import org.example.yodybe.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository colorRepository;

    @Override
    public BaseResponse getAllCategorys() {
        try {
            List<Category> categories = colorRepository.findAll();
            List<SizeDto> categoryDtos = categories.stream().map(this::createCategoryDto).collect(Collectors.toList());
            return new BaseResponse("Thành công", categoryDtos, 200);
        } catch (Exception e) {
            return new BaseResponse("Thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse getCategoryById(Long id) {
        try {
            SizeDto categoryDto = createCategoryDto(colorRepository.findById(id).get());
            return new BaseResponse("Thành công", categoryDto, 200);
        } catch (Exception e) {
            return new BaseResponse("Thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse saveCategory(CategoryForm color) {
        try {
            Category entity = mapToEntity(color);
            colorRepository.save(entity);
            return new BaseResponse("Lưu thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Lưu thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse deleteCategory(Long id) {
        try {
            colorRepository.deleteById(id);
            return new BaseResponse("Xóa thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Xóa thất bại", true, 200);
        }
    }

    @Override
    public BaseResponse updateCategory(Long id, CategoryForm clCategoryForm) {
        try {
            Optional<Category> category = colorRepository.findById(id);
            if (category.isEmpty()) {
                return new BaseResponse("Không tìm thấy màu " + clCategoryForm.getName(), false, 4000);
            }
            category.get().setName(clCategoryForm.getName());
            return new BaseResponse("Cập nhật thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Cập nhật thất bại ", true, 200);
        }
    }

    @Override
    public SizeDto createCategoryDto(Category color) {
        return SizeDto.builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }

    @Override
    public Category mapToEntity(CategoryForm colorForm) {
        return Category.builder()
                .name(colorForm.getName())
                .build();
    }
}
