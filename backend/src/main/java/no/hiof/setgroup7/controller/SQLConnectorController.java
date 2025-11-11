package no.hiof.setgroup7.controller;

import no.hiof.setgroup7.model.SQLConnector;
import no.hiof.setgroup7.DTOs.poiDTO;
import java.util.ArrayList;

import static no.hiof.setgroup7.model.sqlProcedures.SQLGetAllPoi;


public class SQLConnectorController {

    // Reference to the SQLConnector used for database operations
    private SQLConnector sqlConnector;

    public SQLConnectorController() {
        this.sqlConnector = new SQLConnector(); // Correctly initializing SQLConnector
    }

    // Method to fetch POIs using SQLConnector
    public ArrayList<poiDTO> fetchPois() {

        ArrayList<poiDTO> poiList = sqlConnector.getAllPois(SQLGetAllPoi);
        return poiList;
    }
}