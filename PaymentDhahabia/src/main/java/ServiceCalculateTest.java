import com.example.paymentTransaction.Requests.OrderRequest;
import com.example.paymentTransaction.Services.recalculateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;



public class ServiceCalculateTest {

    @InjectMocks
    recalculateService recalculateService;

    OrderRequest orderRequest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        orderRequest = OrderRequest.intialisation();
    }

    @Test
    public void setRecalculateServiceTrueTest() throws SQLException, ClassNotFoundException {

        assertEquals(recalculateService.isCorrecTotalPrice(orderRequest.getProducts(),orderRequest.getPriceTotal()),true);
    }

    @Test
    public void setRecalculateServiceFailedTest() throws SQLException, ClassNotFoundException {

        assertEquals(recalculateService.isCorrecTotalPrice(orderRequest.getProducts(),orderRequest.getPriceTotal()+1),false);
    }

}
