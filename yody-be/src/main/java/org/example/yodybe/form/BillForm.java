package org.example.yodybe.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.yodybe.dto.ProductDto;
import org.example.yodybe.entity.Bill;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.example.yodybe.entity.ProductBill}
 */
@Getter
@Setter
@AllArgsConstructor

public class BillForm implements Serializable {
    Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String customerName;
    @NotNull
    @NotBlank
    @NotEmpty
    private  String province;
    @NotNull
    @NotBlank
    @NotEmpty
    private String district;
    @NotNull
    @NotBlank
    @NotEmpty
    private String addressDetail;
    @NotNull
    @NotBlank
    @NotEmpty
    private  String phone;
    @NotNull
    @NotBlank
    @NotEmpty
    private Double totalPrice;
    private List< ProductBillForm> productBillForm;
}