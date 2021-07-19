package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentBankCard extends PaymentDH {

    private int numBank;
    private String nomBank;
    private String addressBank;

}
