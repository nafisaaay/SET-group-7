package no.hiof.setgroup7.Unittesting;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.repository.SQLConnector;
import no.hiof.setgroup7.repository.SQLProcedures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class SQLdbConnectTest {

    private SQLConnector sqlConnector;
    private SQLProcedures mocksqlProcedures;

    @BeforeEach
    public void setup() {
        sqlConnector = new SQLConnector();
        mocksqlProcedures = mock(SQLProcedures.class);
    }

    @SuppressWarnings("SqlResolve")
    @Test
    public void testGetAllPoi() throws Exception {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mocksqlProcedures.getProcedure()).thenReturn("SELECT * FROM anyTable");

        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("SELECT * FROM anyTable")).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("cLat")).thenReturn("59.1201", "63.4");
        when(mockResultSet.getString("cLon")).thenReturn("10.75", "8.56");
        when(mockResultSet.getString("pName")).thenReturn("Place A", "Place B");
        when(mockResultSet.getString("Location_Type")).thenReturn("Type A", "Type B");
        when(mockResultSet.getString("city")).thenReturn("City A", "City B");
        when(mockResultSet.getString("area")).thenReturn("Area A", "Area B");

        ArrayList<PoiDTO> pois = sqlConnector.getAllPois(mocksqlProcedures);

        Assertions.assertEquals(2, pois.size(), "Expecting 2 entries");
        Assertions.assertEquals("Place A", pois.get(0).getPlaceName());
        Assertions.assertEquals("Place B", pois.get(1).getPlaceName());

        verify(mockConnection).close();
    }

}

