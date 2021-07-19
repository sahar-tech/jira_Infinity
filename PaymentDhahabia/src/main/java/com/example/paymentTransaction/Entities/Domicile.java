package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Domicile extends Delivery {

    private String wilaya;
    private String commune;
    private String postalCode;
    private String numDomicile;
    private String Street;
    private String Avenue;
    private float coastTransport;


}
