package no.hiof.setgroup7.DTOs;

import no.hiof.setgroup7.model.SQLConnector;
import java.util.ArrayList;

import static no.hiof.setgroup7.model.PoIFilter.poiFilter;


import static no.hiof.setgroup7.model.sqlProcedures.*;



public class SQLdbConnect {

    private SQLConnector sqlConnector;

    public SQLdbConnect() {
        this.sqlConnector = new SQLConnector();
    }

    public void run(){

        ArrayList<PoiDTO> poiFredrikstad = sqlConnector.getAllPois(SQLGetFredrikstad);
        ArrayList<PoiDTO> poiSarpsborg = sqlConnector.getAllPois(SQLGetSarpsborg);
        ArrayList<PoiDTO> poiMoss = sqlConnector.getAllPois(SQLGetMoss);
        ArrayList<PoiDTO> poiHalden = sqlConnector.getAllPois(SQLGetHalden);
        ArrayList<PoiDTO> poiOslo = sqlConnector.getAllPois(SQLGetOslo);

        ArrayList SarpFoodPoI = poiFilter(poiSarpsborg, "café", "restaurant", "tavern");
        ArrayList FredFoodPoI = poiFilter(poiFredrikstad, "café", "restaurant", "tavern");
        ArrayList MossFoodPoI = poiFilter(poiMoss, "café", "restaurant", "tavern");
        ArrayList HaldenFoodPoI = poiFilter(poiHalden, "café", "restaurant", "tavern");
        ArrayList OsloFoodPoI = poiFilter(poiOslo, "café", "restaurant", "tavern");

        ArrayList SarpCulturePoI = poiFilter(poiSarpsborg, "museum", "art gallery", "*landmark");
        ArrayList FredCulturePoI = poiFilter(poiFredrikstad, "museum", "art gallery", "*landmark");
        ArrayList MossCulturePoI = poiFilter(poiMoss, "museum", "art gallery", "*landmark");
        ArrayList HaldenCulturePoi = poiFilter(poiHalden, "museum", "art gallery", "*landmark");
        ArrayList OsloCulturePoI = poiFilter(poiOslo, "museum", "art gallery", "*landmark");


    }
}