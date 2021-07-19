package com.example.paymentTransaction.Services;

import com.example.paymentTransaction.Controllers.paymentService;
import com.example.paymentTransaction.Requests.OrderRequest;
import com.paypal.api.payments.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentDHService {

    paymentService paymentService;
    public static final String SUCCESS_URL = "start/exe";
    public static final String CANCEL_URL = "start/cancel";


    public String createPaymentDH(OrderRequest orderRequest){

        Payment P = paymentService.createPayment(orderRequest.getNumOrder(),
                orderRequest.getCurrency(),
                orderRequest.getPriceTotal(),
                "http://localhost:1995/" + CANCEL_URL,
                "http://localhost:1995/" + SUCCESS_URL);

        ///////// here creation payment, start transaction, Open session ////////////////////////
        if(P != null){
            return "link of website payment";
        }else{
            return CANCEL_URL;
        }

    }

    public String executePaymentDH(String paymentId, String payerId){

        Payment P= paymentService.executePayment(paymentId,payerId);

        // Send from site payment link for confirmation the payment transaction
        ///////// here send Dhahabia service :
        ///  paymentId for the information from creationPaymentDH( ) method
        ///  payerId for information buyer that is entered by IHM of site post algeria which generate by first method called
        ///  payment in this session affect the transaction and confirm payment  COMMIT  ////////////////////////

        if(P != null){
            return "link of confirmation payment";
        }else{
            return CANCEL_URL;
        }
    }

}
