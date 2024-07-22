package org.example.yodybe.service;

import jakarta.transaction.Transactional;
import org.example.yodybe.entity.Bill;
import org.example.yodybe.entity.BillDto;
import org.example.yodybe.entity.Product;
import org.example.yodybe.entity.ProductBill;
import org.example.yodybe.form.BillForm;
import org.example.yodybe.repositoties.BillRepository;
import org.example.yodybe.repositoties.ProductBillRepository;
import org.example.yodybe.repositoties.ProductRepository;
import org.example.yodybe.utils.BaseResponse;
import org.example.yodybe.utils.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductBillRepository productBillRepository;
    @Autowired
    ProductRepository productRepository;


    @Override
    public PaginationResponse getAllBill(Integer page, Integer size) {
        try {
            Page<Bill> bills = billRepository.getBillList(PageRequest.of(page, size));
            return paginationResponseHandler(bills);
        } catch (Exception e) {
            return new PaginationResponse("Error getting product list", null, 500, 0, 0, 0);
        }
    }

    @Override
    public PaginationResponse getBillByCustomerName(String name, Integer page, Integer size) {
        try {
            Page<Bill> bills = billRepository.getBillCus(PageRequest.of(page, size),name );
            return paginationResponseHandler(bills);
        } catch (Exception e) {
            return new PaginationResponse("Error getting product list", null, 500, 0, 0, 0);
        }
    }

    private PaginationResponse paginationResponseHandler(Page<Bill> billPage) {
        List<BillDto> dto = billPage.getContent().stream().map(this::createBillDto).toList();
        int currentPage = billPage.getPageable().getPageNumber();
        int totalPages = billPage.getTotalPages();
        long totalElements = billPage.getTotalElements();
        return new PaginationResponse("Product list fetched successfully", dto, 200, currentPage, totalPages, totalElements);
    }

    @Override
    public BaseResponse getBillById(Long id) {
        try {
            BillDto billDto = createBillDto(billRepository.findById(id).get());
            return new BaseResponse("Thành công", billDto, 200);
        } catch (Exception e) {
            return new BaseResponse("Thất bại", true, 400);
        }
    }

    @Transactional
    @Override
    public BaseResponse saveBill(BillForm billForm) {
        try {
            Bill entity = mapToEntity(billForm);
            Bill bill = billRepository.save(entity);
            billForm.getProductBillForm().forEach(b -> {
                Optional<Product> product = productRepository.findById(b.getProductId());
                if (product.isEmpty()) {
                    throw new RuntimeException("Sản phẩm không tồn tại");
                }
                ProductBill productBill = new ProductBill();
                productBill.setProduct(product.get());
                productBill.setPrice(b.getPrice());
                productBill.setQuantity(b.getQuantity());
                productBill.setBill(bill);
                productBillRepository.save(productBill);
            });
            return new BaseResponse("Lưu thành công", true, 200);
        } catch (RuntimeException e) {
            return new BaseResponse(e.getMessage(), true, 400);
        }
    }

    @Override
    public BaseResponse deleteBill(Long id) {
        try {
            Optional<Bill> bill = billRepository.findById(id);
            if (bill.isEmpty()) {
                throw new RuntimeException("Hóa đơn không tồn tại");
            }
            bill.get().setProductBills(null);
            billRepository.deleteById(id);
            return new BaseResponse("Xóa thành công", true, 200);
        }catch (RuntimeException e) {
            return new BaseResponse(e.getMessage(), true, 400);
        }
    }

    @Override
    public BaseResponse updateBill(Long id, BillForm billForm) {
        try {

            Optional<Bill> bill = billRepository.findById(id);
            if (bill.isEmpty()) {
                throw new RuntimeException("Hóa đơn không tồn tại");
            }
            bill.get().setCustomerName(billForm.getCustomerName());
            bill.get().setPhone(billForm.getPhone());
            bill.get().setAddressDetail(billForm.getAddressDetail());
            bill.get().setTotalPrice(billForm.getTotalPrice());
            bill.get().setDistrict(billForm.getDistrict());
            bill.get().setProvince(billForm.getProvince());
            bill.get().setProductBills(null);
            billRepository.save(bill.get());

            billForm.getProductBillForm().forEach(b -> {
                Optional<Product> product = productRepository.findById(b.getProductId());
                if (product.isEmpty()) {
                    throw new RuntimeException("Sản phẩm không tồn tại");
                }
                ProductBill productBill = new ProductBill();
                productBill.setProduct(product.get());
                productBill.setPrice(b.getPrice());
                productBill.setQuantity(b.getQuantity());
                productBillRepository.save(productBill);
            });
            return new BaseResponse("Lưu thành công", true, 200);
        } catch (RuntimeException e) {
            return new BaseResponse(e.getMessage(), true, 400);
        }
    }

    @Override
    public BillDto createBillDto(Bill bill) {
        return BillDto.builder()
                .id(bill.getId())
                .customerName(bill.getCustomerName())
                .phone(bill.getPhone())
                .addressDetail(bill.getAddressDetail())
                .totalPrice(bill.getTotalPrice())
                .productBills(bill.getProductBills())
                .totalPrice(bill.getTotalPrice())
                .createdAt(bill.getCreatedAt())
                .updatedAt(bill.getUpdatedAt())
                .build();
    }

    @Override
    public Bill mapToEntity(BillForm billForm) {
        Bill bill = new Bill();
        bill.setCustomerName(billForm.getCustomerName());
        bill.setPhone(billForm.getPhone());
        bill.setAddressDetail(billForm.getAddressDetail());
        bill.setTotalPrice(billForm.getTotalPrice());
        bill.setDistrict(billForm.getDistrict());
        bill.setProvince(billForm.getProvince());
        return bill;
    }
}
