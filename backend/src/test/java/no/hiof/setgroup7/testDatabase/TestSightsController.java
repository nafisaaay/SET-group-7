package no.hiof.setgroup7.testDatabase;

import io.javalin.http.Context;

public class TestSightsController {
    private final TestSQLdbConnect sqlConnector;

    public TestSightsController(TestSQLdbConnect sqlConnector) {
        this.sqlConnector = sqlConnector;
    }


    public void getSight(Context context) {

        // Her tar vi imot requesten fra frontend og gjør den om til string Java String object

        String city = context.bodyAsClass(String.class);


        try {
            if (city.equals("Velg ønsket by ... ")) {
                System.out.println("Ingen by valgt!");
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
            throw new RuntimeException(e);
        }


        // context.result("Hei, fra Javalin!");
    }
}
