package org.example.yodybe.service;

import org.example.yodybe.dto.ProductBillDto;
import org.example.yodybe.entity.Bill;
import org.example.yodybe.dto.BillDto;
import org.example.yodybe.entity.ProductBill;
import org.example.yodybe.form.BillForm;
import org.example.yodybe.utils.BaseResponse;
import org.example.yodybe.utils.PaginationResponse;

import java.util.List;

public interface BillService {


    BaseResponse getBillById(Long id);

    BaseResponse saveBill(BillForm color);

    BaseResponse deleteBill(Long id);

    BaseResponse updateBill(Long id, BillForm billForm);

    BillDto createBillDto(Bill bill);

    BillDto createBillDto(Bill bill, List<ProductBillDto> p);

    Bill mapToEntity(BillForm billForm);

    PaginationResponse getAllBill(Integer page, Integer size);

    PaginationResponse getBillByCustomerName(String name, Integer page, Integer size);
}
