package com.soccman.crm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crm")
public class CrmController {

    @GetMapping("/customers")
    public List<Customer> customers() {
        return List.of(
                new Customer("C-001", "Nguyen Van A", "ABC Trading", "Quoted", false),
                new Customer("C-002", "Tran Thi B", "Blue Logistics", "Contacted", true)
        );
    }
}
