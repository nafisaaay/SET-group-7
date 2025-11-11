package no.hiof.setgroup7.integration;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.json.JavalinGson;
import no.hiof.setgroup7.DTOs.DataWrapper;
import no.hiof.setgroup7.DTOs.EnturResponse;
import no.hiof.setgroup7.DTOs.TripRequest;
import no.hiof.setgroup7.DTOs.TripResponse;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.controller.TripValidator;
import no.hiof.setgroup7.model.TripPattern;
import no.hiof.setgroup7.repository.TripRepository;
import no.hiof.setgroup7.service.TripService;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EnturClient {
    private String query;
    private TripRequest tripRequest;
    private TripResponse tripResponse;

    public EnturClient() {

    }

    /*
     Tar imot TripRequest fra service-laget slik at vi har tilgang til brukerens reisedata.
    */
    public void getDataFromService(TripRequest tripRequest) {
        this.tripRequest = tripRequest;
    }

    /*
     - Bygger GraphQL-query med variabler og sender request til Entur API.
     - Returnerer deretter responsen som TripResponse.
    */

    public TripResponse sendRequest() {

        TripRequest userRequest = tripRequest;


        // Henter verdier fra TripRequest-objektet slik at vi har dem klar for bruk i entur query'en.
        String fromPlace = userRequest.getFrom().getPlace();
        String fromName = userRequest.getFrom().getName();
        String toPlace = userRequest.getTo().getPlace();
        String toName = userRequest.getTo().getName();
        int numTripPatterns = userRequest.getNumTripPatterns();
        String dateTime = userRequest.getDateTime();

        // Bygger query til entur
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
                              fromPlace { name latitude longitude vertexType }
                              toPlace { name latitude longitude vertexType }
                              line { id name transportMode }
                              steps {
                                distance
                                heading
                                streetName
                                stayOn
                                relativeDirection
                                latitude
                                longitude
                              }
                            }
                            startTime
                            endTime
                          }
                        }
                      }
                """;



        // Lagrer variabelene i key og value slik at vi kan sette de inn GraphQL-spørringen
        Map<String, Object> variables = new HashMap<>();
        variables.put("fromPlace", fromPlace);
        //variables.put("name", fromName);
        variables.put("toPlace", toPlace);
        //variables.put("name", toName);
        variables.put("numTripPatterns", numTripPatterns);
        variables.put("dateTime", dateTime);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", query);
        requestBody.put("variables", variables);


       Gson gson = new Gson();
       String jsonBody = gson.toJson(requestBody);  // Konverterer request til JSON

        // Oppretter HTTP-request til Entur API
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.entur.io/journey-planner/v3/graphql"))
                .header("Content-Type", "application/json")
                .header("ET-Client-Name", "gruppe7-kollektivtransport-app")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            // Konverterer JSON-respons fra Entur til Java-objekter
            EnturResponse enturResponse = gson.fromJson(response.body(), EnturResponse.class);

            if (enturResponse.getData() == null || enturResponse.getData().getTrip() == null) {
                tripResponse = null;
            }

            else {
                tripResponse = enturResponse.getData().getTrip();
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return tripResponse;

    }






}
