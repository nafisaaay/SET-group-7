package no.hiof.setgroup7.Integrationtesting;

import io.javalin.Javalin;
import no.hiof.setgroup7.DTOs.TripInputDTO;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.controller.TripValidator;
import no.hiof.setgroup7.integration.EnturClient;
import no.hiof.setgroup7.service.TripService;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripIntegrationTest {

    @Test
    void testFullTripFlow() throws IOException, InterruptedException {

        // Arrange
        TripInputDTO tripInputDTO;
        TripValidator tripValidator = new TripValidator();
        EnturClient enturClient = new EnturClient();
        TripService tripService = new TripService(enturClient);
        TripResponse tripResponse;
        Customer customer = new Customer();
        TicketService ticketService = new TicketService();

        TripController tripController = new TripController(tripService, customer, ticketService);

        String  userRequest = """
            {
              "tripType": "round_trip",
              "person": "voksen",
              "from": "Oslo S, Oslo",
              "fromPlace": "NSR:StopPlace:59872",
              "to": "Remmen Høgskolen, Halden",
              "toPlace": "NSR:StopPlace:2685",
              "date": "2025-11-19",
              "time": "18:00"
            }
            """;

        Javalin app = Javalin.create().start(0);

        app.post("/api/trip", context -> {
            tripController.getTripFormData(context);
        });

        HttpClient client = HttpClient.newHttpClient();

        int port = app.port();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/trip"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userRequest))
                .build();
        

        // Act
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("tripPatterns"));

        app.stop();
    }

}
