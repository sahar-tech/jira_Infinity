package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PaymentDHAHABIACard extends PaymentDH {

    private int numCard;
    private Date expirationDate;
    private String CvvCard;
    private String fullNameCard;

}
