
import com.example.paymentTransaction.Requests.OrderRequest;
import com.example.paymentTransaction.Services.ValidationInputService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class ServiceValidationTest {
    @InjectMocks
    ValidationInputService validationInputService;

    OrderRequest orderRequest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderRequest = OrderRequest.intialisation();
    }

    @Test
    public void setValidationInputTrueTest() throws SQLException, ClassNotFoundException {

        assertEquals(validationInputService.validationInput(orderRequest),"OK");
    }

    @Test
    public void setValidationInputFailedTest() throws SQLException, ClassNotFoundException {

        orderRequest.setNumOrder("none");
        assertNotEquals(validationInputService.validationInput(orderRequest),"OK");
    }

}
