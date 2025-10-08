package no.hiof.setgroup7;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import no.hiof.setgroup7.controller.TripController;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class App {

    public static String query = "{\n" +
            "  trip(\n" +
            "    from: {place: \"NSR:StopPlace:59872\", name: \"Oslo S\"}\n" +
            "    to: {place: \"NSR:StopPlace:2685\", name: \"Remmen Høgskolen\"}\n" +
            "    numTripPatterns: 3\n" +
            "    dateTime: \"2025-10-06T06:58:00+02:00\"\n" +
            "  ) {\n" +
            "    tripPatterns {\n" +
            "      duration\n" +
            "      legs {\n" +
            "        distance\n" +
            "        expectedEndTime\n" +
            "        expectedStartTime\n" +
            "        fromPlace {\n" +
            "          name\n" +
            "        }\n" +
            "        toPlace {\n" +
            "          name\n" +
            "        }\n" +
            "        line {\n" +
            "          transportMode\n" +
            "          name\n" +
            "        }\n" +
            "        steps {\n" +
            "          distance\n" +
            "          heading\n" +
            "          streetName\n" +
            "          stayOn\n" +
            "          relativeDirection\n" +
            "        }\n" +
            "      }\n" +
            "      endTime\n" +
            "      startTime\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static String responsQ;

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(rule -> rule.anyHost()));
        }).start(5000);

        TripController tripController = new TripController();

        app.get("/api/trip", ctx -> {
            HttpClient client = HttpClient.newHttpClient();

            String body = "{ \"query\": \"" + query.replace("\"", "\\\"").replace("\n", " ") + "\" }";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.entur.io/journey-planner/v3/graphql"))
                    .header("Content-Type", "application/json")
                    .header("ET-Client-Name","grupp7-kollektivtransport-app")
                    .POST(HttpRequest.BodyPublishers.ofString(body)).build();

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ctx.result(response.body());

            ArrayList<String> bodyRes = new ArrayList<>(Collections.singleton(response.body()));
            /*System.out.println(bodyRes.stream().map(p).collect(Collectors.toList()));*/

            ctx.status(response.statusCode());
            ctx.contentType("application/json");



        });


        app.get("/health", ctx -> ctx.result("ok"));

        /*app.post("/form", ctx -> {
            ctx.result("Success, form data er mottatt!");
            ctx.status(200).result("HEI FRA JAVALIN!");
            System.out.println(ctx.body());
        }
        );*/

        app.post("/form", new Handler() {
            @Override
            public void handle(@org.jetbrains.annotations.NotNull Context context) throws Exception {
                tripController.getTrip(context);
            }
        });

    }

}