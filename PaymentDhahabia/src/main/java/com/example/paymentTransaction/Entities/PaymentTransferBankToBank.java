package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
@Getter
@Setter
public class PaymentTransferBankToBank extends PaymentDH {

    private File receipt;


}
