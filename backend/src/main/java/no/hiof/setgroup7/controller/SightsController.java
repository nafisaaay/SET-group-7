package no.hiof.setgroup7.controller;
import io.javalin.http.Context;
import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.database.SQLdbConnect;
import no.hiof.setgroup7.repository.SQLConnector;
import no.hiof.setgroup7.repository.SQLProcedures;

import java.util.ArrayList;


public class SightsController {
    private final SQLdbConnect sqlConnector;

    public SightsController(SQLdbConnect sqlConnector) {
        this.sqlConnector = sqlConnector;
    }


    public void getSight(Context context) {

        // Her tar vi imot requesten fra frontend og gjør den om til string Java String object

        String city = context.bodyAsClass(String.class);


        try {
            if (city.equals("Velg ønsket by ... ")) {
                context.status(400).result("Ingen by valgt!");
                return;
            }

            else if (city.equals("Fredrikstad")) {
                context.status(200);
                context.json(sqlConnector.getArraylistOfFred());
            }

            else if (city.equals("Sarpsborg")) {
                context.status(200);
                context.json(sqlConnector.getArrayListOfSarps());
            }

            else if (city.equals("Moss")) {
                context.status(200);
                context.json(sqlConnector.getArrayListOfMoss());
            }

            else if (city.equals("Halden")) {
                context.status(200);
                context.json(sqlConnector.getArrayListOfHalden());
            }

            else if (city.equals("Oslo")) {
                context.status(200);
                context.json(sqlConnector.getArrayListOfOslo());
            }

        }

        catch (Exception e) {
            context.status(500).result("Server error");
            throw new RuntimeException(e);
        }


        // context.result("Hei, fra Javalin!");
    }
}
