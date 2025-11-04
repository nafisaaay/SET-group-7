package no.hiof.setgroup7;

import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TripResponse tripResponse;

    @Mock
    Customer customer;

    @Test
    void getTickets(){
        TicketService ticketService = new TicketService();
    }


}
