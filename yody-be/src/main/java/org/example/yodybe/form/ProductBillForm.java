package org.example.yodybe.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.example.yodybe.dto.ProductDto;
import org.example.yodybe.entity.Bill;

import java.io.Serializable;

/**
 * DTO for {@link org.example.yodybe.entity.ProductBill}
 */
@Value
public class ProductBillForm implements Serializable {
    Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    Long productId;
    @NotNull
    @NotEmpty
    @NotBlank
    Double price;
    @NotNull
    @NotEmpty
    @NotBlank
    Integer quantity;
}