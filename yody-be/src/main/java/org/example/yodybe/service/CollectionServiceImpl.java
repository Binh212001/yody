package org.example.yodybe.service;

import org.example.yodybe.dto.CollectionDto;
import org.example.yodybe.entity.Collection;
import org.example.yodybe.form.CategoryForm;
import org.example.yodybe.form.CollectionForm;
import org.example.yodybe.repositoties.CollectionRepository;
import org.example.yodybe.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public BaseResponse getAllCollections() {
        try {
            List<Collection> collections = collectionRepository.findAll();
            List<CollectionDto> categoryDtos = collections.stream().map(this::createCollectionDto).collect(Collectors.toList());
            return new BaseResponse("Thành công", categoryDtos, 200);
        } catch (Exception e) {
            return new BaseResponse("Thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse getCollectionById(Long id) {
        try {
            CollectionDto collectionDto = createCollectionDto(collectionRepository.findById(id).get());
            return new BaseResponse("Thành công", collectionDto, 200);
        } catch (Exception e) {
            return new BaseResponse("Thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse saveCollection(CollectionForm collectionForm) {
        try {
            Collection entity = mapToEntity(collectionForm);
            collectionRepository.save(entity);
            return new BaseResponse("Lưu thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Lưu thất bại", true, 400);
        }
    }

    @Override
    public BaseResponse deleteCollection(Long id) {
        try {
            collectionRepository.deleteById(id);
            return new BaseResponse("Xóa thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Xóa thất bại", true, 200);
        }
    }

    @Override
    public BaseResponse updateCollection(Long id, CollectionForm clCollectionForm) {
        try {
            Optional<Collection> category = collectionRepository.findById(id);
            if (category.isEmpty()) {
                return new BaseResponse("Không tìm thấy màu " + clCollectionForm.getName(), false, 4000);
            }
            category.get().setName(clCollectionForm.getName());
            return new BaseResponse("Cập nhật thành công", true, 200);
        } catch (Exception e) {
            return new BaseResponse("Cập nhật thất bại ", true, 200);
        }
    }

    @Override
    public CollectionDto createCollectionDto(Collection collection) {
        return CollectionDto.builder()
                .id(collection.getId())
                .name(collection.getName())
                .build();
    }

    @Override
    public Collection mapToEntity(CollectionForm collectionForm) {
        return Collection.builder()
                .name(collectionForm.getName())
                .build();
    }
}
