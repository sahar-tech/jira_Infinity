import com.example.paymentTransaction.Controllers.emailService;
import com.example.paymentTransaction.Exception.validationException;
import com.example.paymentTransaction.Requests.OrderRequest;
import com.example.paymentTransaction.Services.*;
import com.sendgrid.helpers.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentControlerTest {

    public static final String SUCCESS_URL = "start/success";
    public static final String CANCEL_URL = "start/cancel";

    @Autowired
    private PaymentDHService payService;

    @Autowired
    private recalculateService recalService;

    @Autowired
    private ValidationInputService validInputService;

    @Autowired
    private DisponibilityQuantityService disponibilityQuantityService;

    @Autowired
    private emailService mailService;

    public String paymentDHAHABIA(OrderRequest orderRequest) {

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

        public String executePaymentDHAHABIA(String paymentID, String payerID) {

            String confirmLink = payService.executePaymentDH(paymentID,payerID);

            if (confirmLink.equals(CANCEL_URL)) {

            boolean isValide=mailService.validEmail("hadjer.mimoune@univ-constantine2.dz");

            if(!isValide){
                throw new validationException("email is not exist in server base of mail");
            }

            List<String> files=new ArrayList<>();
            Mail mail= mailService.genererEmail("hadjer.mimoune@univ-constantine2.dz","hadjer.mimoune@univ-constantine2.dz","suj",
                    "Hello, You have a new order for delivery The delivery document is attached below. To check the payment, please see this link BaridiMob",
                    "", files, "","","","","","","","","","",
                    "","","","");

            if (mail==null){
                throw new validationException("Email is not generated");
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
}
