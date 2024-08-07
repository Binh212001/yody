package org.example.yodybe.service;

import jakarta.transaction.Transactional;
import org.example.yodybe.dto.ProductBillDto;
import org.example.yodybe.dto.ProductDto;
import org.example.yodybe.entity.Bill;
import org.example.yodybe.dto.BillDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            Page<Bill> bills = billRepository.getBillCus(PageRequest.of(page, size), name);
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
            Optional<Bill> bill = billRepository.findById(id);
            if (bill.isEmpty()) {
                throw new RuntimeException("Hóa đơn không tồn tại");
            }
            List<ProductBill> productBills = productBillRepository.findByBill(bill.get());
            List<ProductBillDto> productBillDtos = new ArrayList<>();
            for (ProductBill p : productBills) {
                Optional<Product> product = productRepository.findById(p.getProduct().getId());
                if (product.isEmpty()) {
                    throw new RuntimeException("Sản phẩm không tồn tại");
                }
                ProductDto productDto = mapToProductDto(product.get());
                ProductBillDto productBillDto = mapProductBillDto(p,productDto);
                productBillDtos.add(productBillDto);
            }

            BillDto billDto = createBillDto(bill.get(), productBillDtos);
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
                productBill.setColorId(b.getColorId());
                productBill.setSizeId(b.getSizeId());
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
        } catch (RuntimeException e) {
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
                productBill.setColorId(b.getColorId());
                productBill.setSizeId(b.getSizeId());
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
                .totalPrice(bill.getTotalPrice())
                .createdAt(bill.getCreatedAt())
                .updatedAt(bill.getUpdatedAt())
                .build();
    }

    @Override
    public BillDto createBillDto(Bill bill, List<ProductBillDto> p) {
        return BillDto.builder()
                .id(bill.getId())
                .customerName(bill.getCustomerName())
                .phone(bill.getPhone())
                .addressDetail(bill.getAddressDetail())
                .totalPrice(bill.getTotalPrice())
                .totalPrice(bill.getTotalPrice())
                .productBills(p)
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
        return bill;
    }

    public ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categories(product.getCategories())
                .images(product.getImages())
                .colors(product.getColors())
                .sizes(product.getSizes())
                .collection(product.getCollection())
                .build();

    }
    public ProductBillDto mapProductBillDto(ProductBill p, ProductDto pr) {
        ProductBillDto bill = new ProductBillDto();
        bill.setId(p.getId());
        bill.setProduct(pr);
        bill.setPrice(p.getPrice());
        bill.setQuantity(p.getQuantity());
        bill.setColorId(p.getColorId());
        bill.setSizeId(p.getSizeId());
        return bill;

    }
}
