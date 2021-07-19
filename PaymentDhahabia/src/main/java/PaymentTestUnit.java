
        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.when;

        import java.sql.SQLException;
        import java.util.List;

        import com.example.paymentTransaction.Controllers.emailService;
        import com.example.paymentTransaction.Requests.OrderRequest;
        import com.example.paymentTransaction.Services.DisponibilityQuantityService;
        import com.example.paymentTransaction.Services.PaymentDHService;
        import com.example.paymentTransaction.Services.ValidationInputService;
        import com.example.paymentTransaction.Services.recalculateService;
        import com.sendgrid.helpers.mail.Mail;

        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

public class PaymentTestUnit {

    @InjectMocks
    PaymentControlerTest paymentControlerTest;

    @Mock
    DisponibilityQuantityService disponibilityQuantityService;

    @Mock
    PaymentDHService paymentDHService;

    @Mock
    ValidationInputService validationInputService;

    @Mock
    recalculateService recalService;

    @Mock
    emailService emailService;

    @Mock
    OrderRequest orderRequest;

    @Mock
    Mail mail;

    @Mock
    List<String> files;

    @Mock
    Object paymentID;

    @Mock
    Object payertID;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setPaymentControlerTrueTest() throws SQLException, ClassNotFoundException {

        String LINK_PAYMENT = "link of website payment";

        when(validationInputService.validationInput(orderRequest)).thenReturn("ok");
        when(recalService.isCorrecTotalPrice(orderRequest.getProducts(),orderRequest.getPriceTotal())).thenReturn(true);
        when(disponibilityQuantityService.isDisponibleQuantity(orderRequest.getProducts())).thenReturn(true);
        when(paymentDHService.createPaymentDH(orderRequest)).thenReturn(LINK_PAYMENT);

        assertEquals(paymentControlerTest.paymentDHAHABIA(orderRequest),LINK_PAYMENT);
    }

    @Test
    public void setPaymentControlerExceptionTest() throws SQLException, ClassNotFoundException {

        String CANCEL_URL = "start/cancel";

        when(validationInputService.validationInput(orderRequest)).thenReturn("ok");
        when(recalService.isCorrecTotalPrice(orderRequest.getProducts(),orderRequest.getPriceTotal())).thenReturn(true);
        when(disponibilityQuantityService.isDisponibleQuantity(orderRequest.getProducts())).thenReturn(true);
        when(paymentDHService.createPaymentDH(orderRequest)).thenReturn(CANCEL_URL);

        assertEquals(paymentControlerTest.paymentDHAHABIA(orderRequest),CANCEL_URL);
    }

    @Test
    public void setPaymentControlerExecutionTrueTest()
    {
        String SUCCESS_URL = "start/success";

        when(paymentDHService.executePaymentDH(paymentID.toString(),payertID.toString())).thenReturn("link of confirmation payment");
        when(emailService.validEmail("hadjer.mimoune@univ-constantine2.dz")).thenReturn(true);
        when(emailService.validEmail(orderRequest.getEmail())).thenReturn(true);
        when(emailService.genererEmail("hadjer.mimoune@univ-constantine2.dz","aggab.sahar1995@gmail.com","Confirmation Payment","content",orderRequest.getNumOrder(),
                files,orderRequest.getNumDomicile(), orderRequest.getCommune(), orderRequest.getPostalCode(),orderRequest.getStreet(),orderRequest.getPhone(),orderRequest.getEmail(),
                orderRequest.getLink_GPS(),orderRequest.getPointDelivery(),orderRequest.getDelayDelivery(),orderRequest.getCoastTransport(),
                "","",orderRequest.getNumCard(),"")).thenReturn(mail);
        when(emailService.sendEmail(mail)).thenReturn(true);

        assertEquals(paymentControlerTest.executePaymentDHAHABIA(paymentID.toString(),payertID.toString()), SUCCESS_URL);
    }

    @Test
    public void setPaymentControlerExecutionFailedTest()
    {
        String CANCEL_URL = "start/cancel";

        when(paymentDHService.executePaymentDH(paymentID.toString(),payertID.toString())).thenReturn("link of confirmation payment");
        when(emailService.validEmail("hadjer.mimoune@univ-constantine2.dz")).thenReturn(true);
        when(emailService.validEmail(orderRequest.getEmail())).thenReturn(true);
        when(emailService.genererEmail("hadjer.mimoune@univ-constantine2.dz","aggab.sahar1995@gmail.com","Confirmation Payment","content",orderRequest.getNumOrder(),
                files,orderRequest.getNumDomicile(), orderRequest.getCommune(), orderRequest.getPostalCode(),orderRequest.getStreet(),orderRequest.getPhone(),orderRequest.getEmail(),
                orderRequest.getLink_GPS(),orderRequest.getPointDelivery(),orderRequest.getDelayDelivery(),orderRequest.getCoastTransport(),
                "","",orderRequest.getNumCard(),"")).thenReturn(mail);

        when(emailService.sendEmail(mail)).thenReturn(true);

        assertEquals(paymentControlerTest.executePaymentDHAHABIA(paymentID.toString(),payertID.toString()), CANCEL_URL);
    }
}