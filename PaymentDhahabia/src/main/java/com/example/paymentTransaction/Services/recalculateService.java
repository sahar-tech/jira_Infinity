package com.example.paymentTransaction.Services;

import com.example.paymentTransaction.Entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class recalculateService {

    public boolean isCorrecTotalPrice(List<Product> productList, String priceTotal) {
        float p,totalPrice = 0;
        int q;

        for (int i  = 0; i < productList.size(); i++) {

            p= Float.parseFloat(productList.get(i).getPriceUnit());
            q= Integer.parseInt(productList.get(i).getQuantity());
            totalPrice = totalPrice + (p * q);
        }

        if (totalPrice==Float.parseFloat(priceTotal))
             return true;

        return false;
    }

    public static void main(String[] args){
        Product product = new Product();
        List<Product> productList = new ArrayList<>();

        product.setNameProduct("frf");
        product.setQuantity("1");
        product.setUnityProduct("dzd");
        product.setPriceUnit("100");
        productList.add(product);

        Product product1 = new Product();

        product1.setNameProduct("jyf");
        product1.setQuantity("2");
        product1.setUnityProduct("usa");
        product1.setPriceUnit("25");
        productList.add(product1);

        Product product2 = new Product();

        product2.setNameProduct("mol");
        product2.setQuantity("3");
        product2.setUnityProduct("eur");
        product2.setPriceUnit("30");
        productList.add(product2);

        recalculateService r =new recalculateService();

                System.out.println(r.isCorrecTotalPrice(productList,"240"));

    }

}
