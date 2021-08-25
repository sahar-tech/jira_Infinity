package com.example.paymentTransaction.Controllers;

import com.example.paymentTransaction.Exception.validationException;
import com.example.paymentTransaction.Requests.OrderRequest;
import com.example.paymentTransaction.Services.*;
import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;
import com.sendgrid.helpers.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class paymentControllers {

    public static final String SUCCESS_URL_PP = "try/success";
    public static final String CANCEL_URL_PP = "try/cancel";

    public static final String SUCCESS_URL = "start/success";
    public static final String CANCEL_URL = "start/cancel";

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private PaymentDHService payService;

    @Autowired
    private recalculateService recalService;

    @Autowired
    private ValidationInputService validInputService;

    @Autowired
    private DisponibilityQuantityService disponibilityQuantityService;

    private emailService mailService;



    ////////////////////////////////////////////////// Page Home ///////////////////////////////////////////////////////////////////////
    @GetMapping("/")
    public String home() {
        return "home";
    }





    ///////////////////////////////////////////////// PayPal Payment ////////////////////////////////////////////////////////////////////

    @RequestMapping("/try")
    public String paymentPayPal(@ModelAttribute("order") OrderRequest orderRequest) {


        try {
            com.paypal.api.payments.Payment paymentPaypal = paypalService.createPayment(10.0, "EUR", "PAYPAL",
                    "sale", "good product", "http://localhost:1995/" + CANCEL_URL_PP,
                    "http://localhost:1995/" + SUCCESS_URL_PP);

            for (Links link : paymentPaypal.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL_PP)
    public String cancelPayPal() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL_PP)
    public String successPayPal(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            com.paypal.api.payments.Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }




   //////////////////////////////////////////////////// Dhahabia Card Payment /////////////////////////////////////////////////////////////



  // ---------------------------------------------------  Creation de Payment -------------------------------------------------------------

    @RequestMapping("/start")
    public String paymentDHAHABIA(@ModelAttribute("order") OrderRequest orderRequest) {
        String isValidInput = validInputService.validationInput(orderRequest);

        if (!isValidInput.equalsIgnoreCase("ok")) {
            throw new validationException(isValidInput);
        }

        boolean isCorrectTotalPrice = recalService.isCorrecTotalPrice(orderRequest.getProducts(), orderRequest.getPriceTotal());

        if (!isCorrectTotalPrice) {
            throw new validationException("the calculation of total price for is incorrect ");
        }

        boolean isDisponibleQuantity = false;

        try {
            isDisponibleQuantity = disponibilityQuantityService.isDisponibleQuantity(orderRequest.getProducts());

            if (!isDisponibleQuantity) {
                throw new validationException("the quantity of stock is insufficient for order ");
            }

        } catch (SQLException throwables) {
            throw new validationException("there is problem when try to get data from table Product, check for the query syntax, name of table and column ");
        } catch (ClassNotFoundException e) {
            throw new validationException("there is problem when try to connect DB, check for @IP and Port of server DB, user and password, database name ");
        }

        String link = payService.createPaymentDH(orderRequest);

        /// by this object we can get link of post algeria for payment in session of 20 min

        if (link.equalsIgnoreCase(CANCEL_URL)) {
            // throw new validationException("your payment creation is failed");
            return CANCEL_URL;
        }
        return link;
    }

    // -------------------------------------------------------- Execution Payment --------------------------------------------------------

    @GetMapping(value = "start/exe")
    public String executePaymentDHAHABIA(String paymentID, String payerID) {

        String confirmLink = payService.executePaymentDH(paymentID,payerID);

        if (confirmLink.equals(CANCEL_URL)) {

            boolean isValide=mailService.validEmail("hadjer.mimoune@univ-constantine2.dz");

            if(!isValide){
                throw new validationException("email is not exist in server base of mail");
            }

            List<String> files=new ArrayList<>();
            Mail mail= mailService.genererEmail("hadjer.mimoune@univ-constantine2.dz","hadjer.mimoune@univ-constantine2.dz","suj","content","", files,
                    "","","","","","","","","","","","","","");

            if (mail==null){
                throw new validationException("Email is not generate");
            }

            boolean isSend = mailService.sendEmail(mail);

            if (isSend){
                throw new validationException("Email is not send ");
            }

            return SUCCESS_URL;
        }else {
            return CANCEL_URL;
        }
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPayDH(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) { return "success"; }

    @GetMapping(value = CANCEL_URL)
    public String cancelPayDH() {
        return "cancel";
    }

}
