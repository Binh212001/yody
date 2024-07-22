package org.example.yodybe.repositoties;

import org.example.yodybe.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select b from  Bill  b")
    Page<Bill> getBillList(Pageable pageable);
    @Query("select b from  Bill  b where b.customerName LIKE %:name%")
    Page<Bill> getBillCus(Pageable pageable, String name);
}