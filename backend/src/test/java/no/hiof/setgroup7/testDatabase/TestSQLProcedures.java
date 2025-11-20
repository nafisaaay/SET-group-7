package no.hiof.setgroup7.testDatabase;

public class TestSQLProcedures {
    public String getPoisForFredrikstad() {
        return "SELECT * FROM poi WHERE city = 'Fredrikstad'";
    }
}
