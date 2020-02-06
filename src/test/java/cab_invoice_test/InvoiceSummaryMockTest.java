package cab_invoice_test;


import invoice_generator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;


public class InvoiceSummaryMockTest {


    private InvoiceService invoiceService;

    @Mock
    RideRepository rideRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        invoiceService = new InvoiceService(rideRepository);
    }


    @Test
    public void invoiceSummary() throws InvoiceServiceException {
        String userId = "aaa";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        Mockito.doNothing().when(rideRepository).addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary summary = null;
        try {
            summary = invoiceService.getInvoiceSummary(userId);
        } catch (InvoiceServiceException e) {
            e.printStackTrace();
        }
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary, summary);
    }

    @Test
    public void whenGivenUserIdAndRide_ifWrongUserId_shouldThrowException()  {
        try {
            String userId = "userId";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
            };
            Mockito.doNothing().when(rideRepository).addRides(userId, rides);
            InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
            when(rideRepository.getRides("xyz")).thenThrow(new NullPointerException("User id not found"));
            InvoiceSummary summary = invoiceService.getInvoiceSummary("xyz");
            Assert.assertEquals(invoiceSummary, summary);
        }catch (InvoiceServiceException e){
                e.printStackTrace();
        }
    }
}
