package org.example.yodybe.service;

import org.example.yodybe.dto.CollectionDto;
import org.example.yodybe.entity.Collection;
import org.example.yodybe.form.CollectionForm;
import org.example.yodybe.utils.BaseResponse;

public interface CollectionService {
    BaseResponse getAllCollections();

    BaseResponse getCollectionById(Long id);

    BaseResponse saveCollection(CollectionForm collectionForm);

    BaseResponse deleteCollection(Long id);

    BaseResponse updateCollection(Long id, CollectionForm collectionForm);

    CollectionDto createCollectionDto(Collection collection);

    Collection mapToEntity(CollectionForm collectionForm);
}
