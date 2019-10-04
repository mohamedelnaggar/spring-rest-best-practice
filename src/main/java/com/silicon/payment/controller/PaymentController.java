package com.silicon.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @GetMapping("/payments")
    public ResponseEntity getAllPayments() {
        return ResponseEntity.ok(new ArrayList<>());
    }

}
