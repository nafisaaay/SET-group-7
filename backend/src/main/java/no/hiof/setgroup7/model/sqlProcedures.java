package no.hiof.setgroup7.model;

public class sqlProcedures {
    private String procedure;

    public sqlProcedures(String procedure) {
        this.procedure = procedure;
    }


    public String getProcedure() {
        return procedure;
    }

    


    public static final sqlProcedures SQLGetFredrikstad = new sqlProcedures("SELECT * FROM viewFredrikstadPoI;");
    public static final sqlProcedures SQLGetHalden = new sqlProcedures("SELECT * FROM viewHaldenPoI;");
    public static final sqlProcedures SQLGetMoss = new sqlProcedures("SELECT * FROM viewMossPoI;");
    public static final sqlProcedures SQLGetOslo = new sqlProcedures("SELECT * FROM viewOsloPoI;");
    public static final sqlProcedures SQLGetSarpsborg = new sqlProcedures("SELECT * FROM viewSarpsborgpPoI;");
    public static final sqlProcedures SQLGetAllPoi = new sqlProcedures("SELECT * FROM allPoIData;");

    /* Dropped features, possibility of implementing later on
     public static final sqlProcedures SQLGetUserData = new sqlProcedures("");
     public static final sqlProcedures SQLGetFavourites = new sqlProcedures("");
     public static final sqlProcedures createUser = new sqlProcedures(
     "CREATE TABLE userDataTable (userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, emailAddress varchar(255) NOT NULL, userPassword varchar(255) NOT NULL, fav1 INT, fav2, INT, fav3 INT);");
     */

}




