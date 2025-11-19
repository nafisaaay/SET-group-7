package no.hiof.setgroup7.repository;

public class SQLProcedures {
    private String procedure;

    public SQLProcedures(String procedure) {
        this.procedure = procedure;
    }

    public SQLProcedures() {}

    public String getProcedure() {
        return procedure;
    }

    // prewritten queries to be used with SQLConnector
    public static final SQLProcedures SQLGetFredrikstad = new SQLProcedures("SELECT * FROM viewFredrikstadPoI;");
    public static final SQLProcedures SQLGetHalden = new SQLProcedures("SELECT * FROM viewHaldenPoI;");
    public static final SQLProcedures SQLGetMoss = new SQLProcedures("SELECT * FROM viewMossPoI;");
    public static final SQLProcedures SQLGetOslo = new SQLProcedures("SELECT * FROM viewOsloPoI;");
    public static final SQLProcedures SQLGetSarpsborg = new SQLProcedures("SELECT * FROM viewSarsborgpPoI;");

}






