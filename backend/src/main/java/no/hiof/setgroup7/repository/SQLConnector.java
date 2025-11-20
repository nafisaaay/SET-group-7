package no.hiof.setgroup7.repository;

import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.DTOs.userData;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnector {
    /* creating an url which represents the database.
    jbdc = java database connectivity
    mysql refers to the database management system
    itstud.hiof.no is the address where the database is located, 3306 is the default port number
    se25_G7 in all likelihood refers to the working folder for the database
     */
    String url = "jdbc:mysql://itstud.hiof.no:3306/se25_G7";
    String username;
    String password;
    private Connection injectedConnection;

    public SQLConnector() {}
    public SQLConnector(Connection connection) {
        this.injectedConnection = connection;
    }

    public ArrayList<PoiDTO> getAllPois (SQLProcedures sqlProcedures){
            ArrayList<PoiDTO> poiArrayList = new ArrayList<>();
            Connection connection = null;

                // trying to connect to the database based on where url points to, using username and password to verify
                try {
                    Connection conn;

                    if (injectedConnection != null) {
                        connection = injectedConnection;
                    } else {
                        connection = DriverManager.getConnection(url, userData.DBConnector.getUsername(), userData.DBConnector.getPassword()); //tries to connect to the db using url, username and password
                        System.out.println("Connected to database.");
                    }


                    // creating a statement which then is used for calling a prewritten sql query.
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sqlProcedures.getProcedure());

                    // going through each entry until the next line is empty and adding each row to a DTO
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






