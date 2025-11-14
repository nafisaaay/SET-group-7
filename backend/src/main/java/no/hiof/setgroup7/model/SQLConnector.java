package no.hiof.setgroup7.model;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.DTOs.userData;
import java.util.ArrayList;
import java.sql.*;

public class SQLConnector {
    String url = "jdbc:mysql://itstud.hiof.no:3306/se25_G7";

    public SQLConnector() {
    }

        public ArrayList<PoiDTO> getAllPois (sqlProcedures sqlProcedures){
            ArrayList<PoiDTO> poiArrayList = new ArrayList<>();
            Connection connection = null;


                try {
                    connection = DriverManager.getConnection(url, userData.DBConnector.getUsername(), userData.DBConnector.getPassword()); //tries to connect to the db using url, username and password
                    System.out.println("Connected to database.");


                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sqlProcedures.getProcedure());


                    while (resultSet.next()) {
                        float field1 = Float.parseFloat(resultSet.getString("cLat"));
                        float field2 = Float.parseFloat(resultSet.getString("cLon"));
                        String field3 = resultSet.getString("pName");
                        String field4 = resultSet.getString("Location_Type");
                        String field5 = resultSet.getString("city");
                        String field6 = resultSet.getString("area");

                        PoiDTO poiDTOItem = new PoiDTO(field1, field2, field3, field4, field5, field6);
                        poiArrayList.add(poiDTOItem);
                    }

                } catch (SQLException e) {
                    System.err.println("Failed to connect to the database " + e);

                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                            System.out.println("Disconnected successfully.");
                        }
                    } catch (SQLException e) {
                        System.err.println("Error closing connection: " + e);
                    }
                }
                return poiArrayList;


    }
}






