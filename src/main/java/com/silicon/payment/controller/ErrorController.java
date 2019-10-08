package com.silicon.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ErrorController {

    @GetMapping("/errors/{errorReference}")
    public ResponseEntity getError(@PathVariable("errorReference") String errorReference){
        return ResponseEntity.notFound().build();
    }

}
