package no.hiof.setgroup7.testDatabase;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.repository.SQLConnector;

import java.util.ArrayList;

import static no.hiof.setgroup7.repository.PoIFilter.poiFilter;
import static no.hiof.setgroup7.repository.SQLProcedures.*;
import static no.hiof.setgroup7.repository.SQLProcedures.SQLGetHalden;
import static no.hiof.setgroup7.repository.SQLProcedures.SQLGetOslo;

public class TestSQLdbConnect {
    private ArrayList<ArrayList<PoiDTO>> arrayListOfFred;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfSarps;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfMoss;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfHalden;
    private ArrayList<ArrayList<PoiDTO>> arrayListOfOslo;

    private TestSQLConnector sqlConnector;

    public TestSQLdbConnect() {
        this.sqlConnector = new TestSQLConnector();
    }

    public TestSQLdbConnect(TestSQLConnector connector) {
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

        ArrayList<PoiDTO> SarpCulturePoI = poiFilter(poiSarpsborg, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> FredCulturePoI = poiFilter(poiFredrikstad, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> MossCulturePoI = poiFilter(poiMoss, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> HaldenCulturePoi = poiFilter(poiHalden, "museum", "art gallery", "*landmark");
        ArrayList<PoiDTO> OsloCulturePoI = poiFilter(poiOslo, "museum", "art gallery", "*landmark");


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
