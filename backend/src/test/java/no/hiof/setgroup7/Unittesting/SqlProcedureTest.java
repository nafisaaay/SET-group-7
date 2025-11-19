package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.repository.SQLProcedures;
import no.hiof.setgroup7.repository.SQLProcedures;
import no.hiof.setgroup7.repository.SQLProcedures.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SqlProcedureTest {


    @Test
    public void testConstructorAndGetProcedure() {
        SQLProcedures sql = new SQLProcedures("SELECT * FROM blabla;");
        Assertions.assertEquals("SELECT * FROM blabla;", sql.getProcedure());
    }

    @Test
    public void testSQLGetFredrikstad() {
        Assertions.assertEquals("SELECT * FROM viewFredrikstadPoI;", SQLProcedures.SQLGetFredrikstad.getProcedure());
    }

    @Test
    public void testSQLGetHalden() {
        Assertions.assertEquals("SELECT * FROM viewHaldenPoI;", SQLProcedures.SQLGetHalden.getProcedure());
    }

    @Test
    public void testSQLGetMoss() {
        Assertions.assertEquals("SELECT * FROM viewMossPoI;", SQLProcedures.SQLGetMoss.getProcedure());
    }

    @Test
    public void testSQLGetOslo() {
        Assertions.assertEquals("SELECT * FROM viewOsloPoI;", SQLProcedures.SQLGetOslo.getProcedure());
    }

    @Test
    public void testSQLGetSarpsborg() {
        Assertions.assertEquals("SELECT * FROM viewSarsborgpPoI;", SQLProcedures.SQLGetSarpsborg.getProcedure());
    }

}