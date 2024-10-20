package com.damian.drools.model;

import com.damian.drools.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@AllArgsConstructor
@Data
public class Order implements Serializable {
    private String name;
    private CardType cardType;
    private double discount;
    private double price;
}
