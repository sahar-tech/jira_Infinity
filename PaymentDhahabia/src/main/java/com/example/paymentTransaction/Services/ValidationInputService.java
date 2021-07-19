package com.example.paymentTransaction.Services;

import com.example.paymentTransaction.Entities.Product;
import com.example.paymentTransaction.Exception.validationException;
import com.example.paymentTransaction.Requests.OrderRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationInputService {

    public String validationInput(@org.jetbrains.annotations.NotNull OrderRequest orderRequest) throws validationException {

        List<String> listInputsValue = new ArrayList<>();

        //------------------------------------------ Order ----------------------------------------------------------//

        if(StringUtils.isBlank(orderRequest.getNumOrder())|| !StringUtils.isNumericSpace(orderRequest.getNumOrder())){
            return "for num order is invalid ";
        }
        if(StringUtils.isBlank(orderRequest.getCurrency())|| !isExistCurrency(orderRequest.getCurrency())){
            return "for currency is invalid ";
        }
        if(StringUtils.isBlank(orderRequest.getPriceTotal())|| !StringUtils.isNumericSpace(orderRequest.getPriceTotal())){
            return "for price total is invalid ";
        }
        if(StringUtils.isBlank(orderRequest.getPriceTTC())|| !StringUtils.isNumericSpace(orderRequest.getPriceTTC())){
            return "for TTC is invalid ";
        }

        //------------------------------------------ Buyer ----------------------------------------------------------//

        if(!StringUtils.isBlank(orderRequest.getBuyer())){  //  enume = {'personne','entreprise'}

            if(orderRequest.getBuyer().equalsIgnoreCase("personne")){

                if(StringUtils.isBlank(orderRequest.getName())){
                    return "for name bayer is empty ";
                }
                if(StringUtils.isBlank(orderRequest.getFamilyName())){
                    return "for family name buyer is empty ";
                }

            }else{      // Entreprise

                if(StringUtils.isBlank(orderRequest.getNameEnterprise())){
                    return "for name of enterprise is empty ";
                }
                if(StringUtils.isBlank(orderRequest.getNIF())|| ! isValidSyntaxNIF(orderRequest.getNIF())){
                    return "for NIF is invalid ";
                }
                if(StringUtils.isBlank(orderRequest.getArticle())){
                    return "for article is empty ";
                }
                if(StringUtils.isBlank(orderRequest.getAddressEnterprise())){
                    return "for address enterprise is invalid ";
                }
            }

            if(StringUtils.isBlank(orderRequest.getEmail())|| !isValidSyntaxEmail(orderRequest.getEmail())){
                return "for email buyer is invalid ";
            }
            if(StringUtils.isBlank(orderRequest.getPhone())|| !isValidSyntaxPhone(orderRequest.getPhone())){
                return "for phone buyer is invalid ";
            }

        }else{
            return "for buyer type is invalid ";
        }

        //------------------------------------------ Delivery ----------------------------------------------------------//

        if(!StringUtils.isBlank(orderRequest.getDelivery())) {        //  enume = {'domicile','pointLiv','pointSeller'}

            if (orderRequest.getDelivery().equalsIgnoreCase("domicile")) {

                if (StringUtils.isBlank(orderRequest.getWilaya())|| !isExistWilaya(orderRequest.getWilaya())) {
                    return "for wilaya of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getCommune())|| !isExistCommune(orderRequest.getCommune())) {
                    return "for commune of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getPostalCode())|| !isValidSyntaxPostalCode(orderRequest.getPostalCode())) {
                    return "for postal code of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getNumDomicile())) {
                    return "for domicile of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getStreet())) {
                    return "for street of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getAvenue())) {
                    return "for avenue of buyer is invalid ";
                }
                if (StringUtils.isBlank(orderRequest.getCoastTransport())||!StringUtils.isNumericSpace(orderRequest.getCoastTransport())) {
                    return "for coast of transport is invalid ";
                }
            }

            if (orderRequest.getDelivery().equalsIgnoreCase("pointLiv")) {

                if (StringUtils.isBlank(orderRequest.getPointDelivery())) {
                    return "for point of delivery is invalid ";
                }
            }

            if (orderRequest.getDelivery().equalsIgnoreCase("pointSeller")) {

                if (StringUtils.isBlank(orderRequest.getAddressDelivery())) {
                    return "for address of seller is invalid ";
                }
            }

            if (StringUtils.isBlank(orderRequest.getLink_GPS())|| !isValidLinkGPS(orderRequest.getLink_GPS())) {
                return "for link GPS is invalid ";
            }
            if (StringUtils.isBlank(orderRequest.getDelayDelivery())||!StringUtils.isNumericSpace(orderRequest.getDelayDelivery())) {
                return "for delay of delivery is invalid ";
            }

        }else{
            return "for type of delivery is invalid ";
        }

        //------------------------------------------ Payment ----------------------------------------------------------//

        if(StringUtils.isBlank(orderRequest.getPayment())) {                 // enum = {'dhahabia','cib','virment'};
            return "for type of payment is invalid ";
        }

        return "OK";
    }

    private boolean isValidLinkGPS(String link_gps) {


        return true;
    }

    private boolean isValidSyntaxNIF(String nif) {

        return true;
    }

    private boolean isExistCurrency(String currency) {

        return true;
    }

    private boolean isExistWilaya(String wilaya) {

        return true;
    }

    private boolean isExistCommune(String commune) {

        return true;
    }

    private boolean isValidSyntaxEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean isValidSyntaxPhone(String s) {

        Pattern p = Pattern.compile("^(0){1}[5-7]{1}\\d{8}$");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private boolean isValidSyntaxPostalCode(String s) {

        Pattern p = Pattern.compile("[0-6]{1}[0-9]{4}");
        Matcher m = p.matcher(s);
         return (m.find() && m.group().equals(s));
    }

    public static void main(String[] args){

        ValidationInputService v = new ValidationInputService();

        System.out.println(v.validationInput(OrderRequest.intialisation()));
    }

}
