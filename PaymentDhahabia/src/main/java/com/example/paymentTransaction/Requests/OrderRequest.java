package com.example.paymentTransaction.Requests;

import com.example.paymentTransaction.Entities.Product;
import lombok.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    ///// Product Information
    private List<Product> products;

    ///// Order Information
    private String numOrder;
    private String currency;
    private String priceTotal;
    private String priceTTC;

    // private enum buyer {person,enterprise};
    private String buyer;

    ///// buyer person Information
    private String name;
    private String familyName;
    private String email;
    private String phone;

    ///// buyer Enterprise Information
    private String nameEnterprise;
    private String NIF;
    private String article;
    private String addressEnterprise;

    // private enum delivery{domicile,pointDelivery,pointSeller};
    private String delivery;

    ///// delivery ---> domicile Information
    private String wilaya;
    private String commune;
    private String postalCode;
    private String numDomicile;
    private String Street;
    private String Avenue;
    private String coastTransport;

    private String link_GPS;
    private String delayDelivery;

    ///// delivery ---> point of delivery Information
    private String pointDelivery;

    ///// delivery ---> point seller point Information
    private String addressDelivery;

    // private enum payment{dhahabia,cib,virment};
    private String payment;

    ///// DHAHABIA  Card Information
    private String numCard;
    private Date expirationDate;
    private String CvvCard;
    private String fullNameCard;

    ///// Bank Card Information
    private int numBank;
    private String nomBank;
    private String addressBank;

    ///// Transfer from account to account Information
    private File receipt;

    ///// Facture Information
    private String priceTTA;

    public static OrderRequest intialisation (){

        OrderRequest orderRequest = new OrderRequest();
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

        orderRequest.setNumOrder("1320120");
        orderRequest.setProducts(productList);
        orderRequest.setPriceTotal("240");
        orderRequest.setCurrency("dzd");

        orderRequest.setAvenue("8mai1945");
        orderRequest.setStreet("132");
        orderRequest.setCommune("elOued");
        orderRequest.setWilaya("El-Oued");
        orderRequest.setNumDomicile("39");
        orderRequest.setPostalCode("39012");
        orderRequest.setLink_GPS("http://linkgps.com.au/");
        orderRequest.setPhone("0697935487");
        orderRequest.setEmail("aggab.sahar1995@gmil.com");
        orderRequest.setCoastTransport("60");

        orderRequest.setName("SAHAR");
        orderRequest.setFamilyName("AGGAB");

        orderRequest.setDelivery("domicile");
        orderRequest.setPayment("dhahabia");
        orderRequest.setBuyer("personne");
        orderRequest.setDelayDelivery("20");
        orderRequest.setPriceTTC("100");

        return orderRequest;
    }


}
