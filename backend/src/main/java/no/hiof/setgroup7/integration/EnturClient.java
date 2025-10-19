package no.hiof.setgroup7.integration;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.json.JavalinGson;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.service.TripService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Objects;

public class EnturClient {
    private String query;
    private TripRequest tripRequest;
    private TripResponse tripResponse;

    public EnturClient() {

    }

    public void getDataFromService(TripRequest tripRequest) {
        this.tripRequest = tripRequest;
    }

    public void sendRequest(Context ctx) {
        TripRequest userRequest = tripRequest;

        String fromPlace = userRequest.getFrom().getPlace();
        String fromName = userRequest.getFrom().getName();
        String toPlace = userRequest.getTo().getPlace();
        String toName = userRequest.getTo().getName();
        int numTripPatterns = userRequest.getNumTripPatterns();
        String dateTime = userRequest.getDateTime();

        query = """
                      query Trip($fromPlace: String!, $toPlace: String!, $numTripPatterns: Int!, $dateTime: DateTime!) {
                        trip(
                          from: { place: $fromPlace }
                          to: { place: $toPlace }
                          numTripPatterns: $numTripPatterns
                          dateTime: $dateTime
                        ) {
                          tripPatterns {
                            duration
                            legs {
                              distance
                              expectedEndTime
                              expectedStartTime
                              fromPlace { name }
                              toPlace { name }
                              line { name transportMode }
                              steps {
                                distance
                                heading
                                streetName
                                stayOn
                                relativeDirection
                              }
                            }
                            startTime
                            endTime
                          }
                        }
                      }
                """;




        Map<String, Object> variables = Map.of(
                "fromPlace", fromPlace,
                "toPlace", toPlace,
                "numTripPatterns", numTripPatterns,
                "dateTime", dateTime
        );


        Map<String, Object> requestBody = Map.of(
                "query", query,
                "variables", variables
        );

       Gson gson = new Gson();
       String jsonBody = gson.toJson(requestBody);

       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.entur.io/journey-planner/v3/graphql"))
                .header("Content-Type", "application/json")
                .header("ET-Client-Name", "your-app-name")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ctx.contentType("application/json");
            System.out.println(ctx.result(response.body()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            ctx.status(500).result("Feil ved henting av data fra Entur API");
        }


    }

}
