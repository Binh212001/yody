package org.example.yodybe.controllers;

import org.example.yodybe.form.BillForm;
import org.example.yodybe.service.BillService;
import org.example.yodybe.utils.BaseResponse;
import org.example.yodybe.utils.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    // TODO: Implement bill related API endpoints here.
    //GET: /api/bills
    @Autowired
    BillService billService;
    @GetMapping
    public ResponseEntity<PaginationResponse> getAllBill(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        PaginationResponse paginationResponse= billService.getAllBill(page,size);
        return ResponseEntity.ok(paginationResponse);
    }

    //GET: /api/bills/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBillById(@PathVariable Long id) {
        BaseResponse baseResponse= billService.getBillById(id);
        return ResponseEntity.ok(baseResponse);
    }
    //POST: /api/bills
    @PostMapping
    public ResponseEntity<BaseResponse> createBill(@RequestBody BillForm bill) {
        BaseResponse response= billService.saveBill(bill);
        return ResponseEntity.ok(response);
    }
    //PUT: /api/bills/{id}
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBill(@PathVariable Long id, @RequestBody BillForm billDetails) {
        BaseResponse response= billService.updateBill(id, billDetails);
        return ResponseEntity.ok(response);
    }
    //DELETE: /api/bills/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBill(@PathVariable Long id) {
        BaseResponse response= billService.deleteBill(id);
        return ResponseEntity.ok(response);
    }
    //GET: /api/bills/customer/{name}

    @GetMapping("/customer/{name}")
    public ResponseEntity<PaginationResponse> getBillByCustomerName(

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "name", defaultValue = "0") String name,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        PaginationResponse paginationResponse= billService.getBillByCustomerName(name,page,size);
        return ResponseEntity.ok(paginationResponse);
    }
}
