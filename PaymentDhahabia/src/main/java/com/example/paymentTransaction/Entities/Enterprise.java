package com.example.paymentTransaction.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enterprise extends Buyer {

    private String NIF;
    private String article;
}
