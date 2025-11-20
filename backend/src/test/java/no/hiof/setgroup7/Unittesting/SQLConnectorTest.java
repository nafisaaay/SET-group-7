package no.hiof.setgroup7.Unittesting;


import static org.mockito.Mockito.*;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.repository.SQLConnector;
import no.hiof.setgroup7.repository.SQLProcedures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;



import java.util.ArrayList;

public class SQLConnectorTest {

    private SQLConnector sqlConnector;
    private SQLProcedures mockSqlProcedures;

    @BeforeEach
    public void setup(){
        sqlConnector = new SQLConnector();
        mockSqlProcedures = mock(SQLProcedures.class);
    }

    @Test
    public void testGetAllPoiWorks() throws Exception {
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        sqlConnector = new SQLConnector(mockConnection);

        when(mockSqlProcedures.getProcedure()).thenReturn("SELECT * FROM mockView");
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("cLat")).thenReturn("59.9958", "58.9999");
        when(mockResultSet.getString("cLon")).thenReturn("10.0000", "9.9999");
        when(mockResultSet.getString("pName")).thenReturn("Place 1", "Place 2");
        when(mockResultSet.getString("Location_Type")).thenReturn("Type 1", "Type 2");
        when(mockResultSet.getString("city")).thenReturn("City 1", "City 2");
        when(mockResultSet.getString("area")).thenReturn("Area 1", "Area 2");

        ArrayList<PoiDTO> poiDTOS = sqlConnector.getAllPois(mockSqlProcedures);

        Assertions.assertEquals(2, poiDTOS.size());
        Assertions.assertEquals("Place 1", poiDTOS.get(0).getPlaceName());
        Assertions.assertEquals("Place 2", poiDTOS.get(1).getPlaceName());

        verify(mockConnection).close();
    }


    /*@Test
    public void testGetAllPoiException()  throws Exception {

        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException("Unable to connect to the DB Error"));

        SQLConnector sqlConnector = new SQLConnector();

        ArrayList<PoiDTO> poiDTOS = sqlConnector.getAllPois(mockSqlProcedures);

        Assertions.assertEquals(0, poiDTOS.size(), "Expecting empty array if connection failure.");
    }*/
}