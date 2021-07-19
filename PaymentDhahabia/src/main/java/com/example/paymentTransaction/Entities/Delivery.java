package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Delivery {

    private int deliveryID;
    private String email;
    private int phone;
    private String link_GPS;
    private int delayDelivery;

}
