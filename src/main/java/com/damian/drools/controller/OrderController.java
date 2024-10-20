package com.damian.drools.controller;

import com.damian.drools.model.Order;

import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final KieSession kieSession;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        kieSession.insert(order);
        kieSession.fireAllRules();
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
