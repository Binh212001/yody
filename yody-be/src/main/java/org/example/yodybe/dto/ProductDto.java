package org.example.yodybe.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.yodybe.entity.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
    Double price;
}