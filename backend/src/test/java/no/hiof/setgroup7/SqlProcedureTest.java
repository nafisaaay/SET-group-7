package no.hiof.setgroup7;

import no.hiof.setgroup7.model.sqlProcedures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import no.hiof.setgroup7.model.sqlProcedures.*;

public class SqlProcedureTest {


    @Test
    public void testConstructorAndGetProcedure() {
        sqlProcedures sql = new sqlProcedures("SELECT * FROM blabla;");
        Assertions.assertEquals("SELECT * FROM blabla;", sql.getProcedure());
    }

    @Test
    public void testSQLGetFredrikstad() {
        Assertions.assertEquals("SELECT * FROM viewFredrikstadPoI;", sqlProcedures.SQLGetFredrikstad.getProcedure());
    }

    @Test
    public void testSQLGetHalden() {
        Assertions.assertEquals("SELECT * FROM viewHaldenPoI;", sqlProcedures.SQLGetHalden.getProcedure());
    }

    @Test
    public void testSQLGetMoss() {
        Assertions.assertEquals("SELECT * FROM viewMossPoI;", sqlProcedures.SQLGetMoss.getProcedure());
    }

    @Test
    public void testSQLGetOslo() {
        Assertions.assertEquals("SELECT * FROM viewOsloPoI;", sqlProcedures.SQLGetOslo.getProcedure());
    }

    @Test
    public void testSQLGetSarpsborg() {
        Assertions.assertEquals("SELECT * FROM viewSarsborgpPoI;", sqlProcedures.SQLGetSarpsborg.getProcedure());
    }

}