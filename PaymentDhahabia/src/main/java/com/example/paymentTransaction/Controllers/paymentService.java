package com.example.paymentTransaction.Controllers;

import com.paypal.api.payments.Payment;

public interface paymentService {

    Payment createPayment(String numOrder, String unit, String priceTotal, String cancel, String success);
    Payment executePayment(String paymentID, String payerID);
}
