package no.hiof.setgroup7.DTOs;
import java.sql.*;

//this function only works in all likelihood when connected to the school VPN or when connected to school internet

public class SQLdbConnect {

    public void SQLdbConnectMethod() {


        String url = "jdbc:mysql://itstud.hiof.no:3306/se25_G7"; // Url, username and password should be instead fetched using <object>.getUrl, <object>.getUsername, <object>.getPassword,
        String username = "gruppe7"; //username should be abstracted
        String password = "Summer26"; //should be abstracted again
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connection to the database.");

            String selectView = "SELECT * FROM allPoIData;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectView);
            System.out.println(resultSet);

            while (resultSet.next()) {
                // Replace 'field1', 'field2', etc., with actual column names from the view
                String field1 = resultSet.getString("cLat");
                String field2 = resultSet.getString("cLon");
                String field3 = resultSet.getString("pName");
                String field4 = resultSet.getString("Location_Type");
                String field5 = resultSet.getString("city");
                String field6 = resultSet.getString("area");

                System.out.println("Latitude: " + field1 + ", Longtitude: " + field2 + ", Name: " + field3 + ", Type: " + field4 + ", Town: " + field5 + ", Area: " + field6);
            }

            /* tested successfully the following query to see if the connection worked
            String createTableSQL = "CREATE TABLE Car ("
                    + "brand VARCHAR(50), "
                    + "colour VARCHAR(50))";

             */

            statement.execute(selectView);

            //CallableStatement cs=connection.prepareCall("{call <procedureName(?)>}");

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
    }


        public static void main(String[] args) {
        SQLdbConnect sqLdbConnect = new SQLdbConnect();

        sqLdbConnect.SQLdbConnectMethod();

    }

}
