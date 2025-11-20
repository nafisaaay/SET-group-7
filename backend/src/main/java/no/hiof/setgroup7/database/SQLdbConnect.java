package no.hiof.setgroup7.database;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.repository.SQLConnector;

import java.util.ArrayList;

import static no.hiof.setgroup7.repository.PoIFilter.poiFilter;
import static no.hiof.setgroup7.repository.SQLProcedures.*;


public class SQLdbConnect {
    private ArrayList<ArrayList<PoiDTO>> arrayListOfFred;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfSarps;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfMoss;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfHalden;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfOslo;

    private SQLConnector sqlConnector;

    public SQLdbConnect() {
        this.sqlConnector = new SQLConnector();
    }

    public SQLdbConnect(SQLConnector connector) {
        this.sqlConnector = connector;
    }

    // Works similarly to main class, but is called instead of ran. Populating array lists with relevant data from DB
    public void run(){

        ArrayList<PoiDTO> poiFredrikstad = sqlConnector.getAllPois(SQLGetFredrikstad);
        ArrayList<PoiDTO> poiSarpsborg = sqlConnector.getAllPois(SQLGetSarpsborg);
        ArrayList<PoiDTO> poiMoss = sqlConnector.getAllPois(SQLGetMoss);
        ArrayList<PoiDTO> poiHalden = sqlConnector.getAllPois(SQLGetHalden);
        ArrayList<PoiDTO> poiOslo = sqlConnector.getAllPois(SQLGetOslo);

        ArrayList<PoiDTO> SarpFoodPoI = poiFilter(poiSarpsborg, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> FredFoodPoI = poiFilter(poiFredrikstad, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> MossFoodPoI = poiFilter(poiMoss, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> HaldenFoodPoI = poiFilter(poiHalden, "café", "restaurant", "tavern");
        ArrayList<PoiDTO> OsloFoodPoI = poiFilter(poiOslo, "café", "restaurant", "tavern");

        ArrayList<PoiDTO> SarpCulturePoI = poiFilter(poiSarpsborg, "sightseeing spot", "art gallery", "*landmark");
        ArrayList<PoiDTO> FredCulturePoI = poiFilter(poiFredrikstad, "museum", "historical landmark", "park");
        ArrayList<PoiDTO> MossCulturePoI = poiFilter(poiMoss, "museum", "historical landmark", "sightseeing spot");
        ArrayList<PoiDTO> HaldenCulturePoi = poiFilter(poiHalden, "museum", "park", "*landmark");
        ArrayList<PoiDTO> OsloCulturePoI = poiFilter(poiOslo, "museum", "park", "sightseeing spot");


        arrayListOfFred = new ArrayList<ArrayList<PoiDTO>>();
        arrayListOfFred.add(FredFoodPoI);
        arrayListOfFred.add(FredCulturePoI);

        arrayListOfSarps = new ArrayList<ArrayList<PoiDTO>>();
        arrayListOfSarps.add(SarpFoodPoI);
        arrayListOfSarps.add(SarpCulturePoI);


        arrayListOfMoss = new ArrayList<ArrayList<PoiDTO>>();
        arrayListOfMoss.add(MossFoodPoI);
        arrayListOfMoss.add(MossCulturePoI);

        arrayListOfHalden = new ArrayList<ArrayList<PoiDTO>>();
        arrayListOfHalden.add(HaldenFoodPoI);
        arrayListOfHalden.add(HaldenCulturePoi);

        arrayListOfOslo = new ArrayList<ArrayList<PoiDTO>>();
        arrayListOfOslo.add(OsloFoodPoI);
        arrayListOfOslo.add(OsloCulturePoI);

        System.out.println(SarpCulturePoI);

    }

    public ArrayList<ArrayList<PoiDTO>> getArraylistOfFred() {
        return arrayListOfFred;
    }

    public ArrayList<ArrayList<PoiDTO>> getArrayListOfSarps() {
        return arrayListOfSarps;
    }

    public ArrayList<ArrayList<PoiDTO>> getArrayListOfMoss() {
        return arrayListOfMoss;
    }

    public ArrayList<ArrayList<PoiDTO>> getArrayListOfHalden() {
        return arrayListOfHalden;
    }

    public ArrayList<ArrayList<PoiDTO>> getArrayListOfOslo() {
        return arrayListOfOslo;
    }
}
