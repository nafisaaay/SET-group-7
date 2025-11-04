package no.hiof.setgroup7;

import io.javalin.Javalin;

import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.integration.EnturClient;
import no.hiof.setgroup7.service.TripService;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;


public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(rule -> rule.anyHost()));
        }).start(8000);

        EnturClient client = new EnturClient();

        TripService tripService = new TripService(client);
        Customer customer = new Customer();
        TicketService ticketService = new TicketService();
        TripController tripController = new TripController(tripService, customer, ticketService);

        app.post("/api/trip", context -> {
            tripController.getTripFormData(context);
        });

        app.get("/health", context -> context.result("ok"));

    }

}