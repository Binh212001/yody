package org.example.yodybe.repositoties;

import org.example.yodybe.entity.Bill;
import org.example.yodybe.entity.ProductBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductBillRepository extends JpaRepository<ProductBill, Long> {
    List<ProductBill> findByBill(Bill bill);
}