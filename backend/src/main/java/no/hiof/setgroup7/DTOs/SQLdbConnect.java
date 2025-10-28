package no.hiof.setgroup7.DTOs;
import java.sql.*;


public class SQLdbConnect {

    public void SQLdbConnectMethod() {


        String url = "jdbc:mysql://itstud.hiof.no:3306/se25_G7"; // The url for the database, should adjust it for actual one
        String username = "gruppe7"; //username should be abstracted
        String password = "Summer26"; //should be abstracted again
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connection to the database.");


            String createTableSQL = "CREATE TABLE Car ("
                    + "brand VARCHAR(50), "
                    + "colour VARCHAR(50))";

            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table 'Car' created successfully.");

            //CallableStatement cs=connection.prepareCall("{call <procedureName(?)>}");

        } catch(SQLException e) {
            System.err.println("Failed to connect to the database " +e);


        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Disconnected successfully.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " +e); }
        }
    }

    public static void main(String[] args) {
        SQLdbConnect sqLdbConnect = new SQLdbConnect();

        sqLdbConnect.SQLdbConnectMethod();

    }

}
