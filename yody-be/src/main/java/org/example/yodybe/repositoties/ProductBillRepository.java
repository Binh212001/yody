package org.example.yodybe.repositoties;

import org.example.yodybe.entity.ProductBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBillRepository extends JpaRepository<ProductBill, Long> {
}