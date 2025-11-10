package no.hiof.setgroup7.DTOs;

import no.hiof.setgroup7.model.SQLConnector;
import static no.hiof.setgroup7.model.sqlProcedures.SQLGetAllPoi;
import java.util.ArrayList;

public class SQLdbConnect {
    public static void main(String[] args) {

        System.out.println(SQLGetAllPoi.getProcedure());

        SQLConnector sqlConnector = new SQLConnector();

        ArrayList<poiDTO> poiList = sqlConnector.getAllPois(SQLGetAllPoi);

        // Print out the list of POIs
        for (poiDTO poi : poiList) {
            System.out.println(poi);
        }
    }
}