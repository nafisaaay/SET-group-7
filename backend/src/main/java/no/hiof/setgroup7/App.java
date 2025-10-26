package no.hiof.setgroup7;

import io.javalin.Javalin;

import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.integration.EnturClient;
import no.hiof.setgroup7.service.TripService;


public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(rule -> rule.anyHost()));
        }).start(5000);

        EnturClient client = new EnturClient();

        TripService tripService = new TripService(client);
        TripController tripController = new TripController(tripService);

        app.post("/api/trip", context -> {
            tripController.getTripFormData(context);
        });

        app.get("/health", context -> context.result("ok"));

    }

}