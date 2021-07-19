package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    private int orderID;
    private String numOrder;
    private String unit;
    private String priceTotal;
    private String priceTTC;
    private List<Product> products;

}
