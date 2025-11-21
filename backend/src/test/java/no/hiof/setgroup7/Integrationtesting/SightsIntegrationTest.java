package no.hiof.setgroup7.Integrationtesting;

import io.javalin.Javalin;
import no.hiof.setgroup7.testDatabase.TestSQLConnector;
import no.hiof.setgroup7.testDatabase.TestSQLdbConnect;
import no.hiof.setgroup7.testDatabase.TestSightsController;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SightsIntegrationTest {

    @Test
    void testFullSightsFlow() throws Exception {

        // Arrange
        String h2Url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

        Connection conn = DriverManager.getConnection(h2Url, "test", "test123");
        Statement stmt = conn.createStatement();

        // Oppretter tabellen
        stmt.execute("""
        CREATE TABLE poi (
            cLat FLOAT,
            cLon FLOAT,
            pName VARCHAR(255),
            Location_Type VARCHAR(100),
            city VARCHAR(100),
            area VARCHAR(100)
        );
    """);

        // Setter inn testdata kun for Fredrikstad
        stmt.execute("""
        INSERT INTO poi (cLat, cLon, pName, Location_Type, city, area)
        VALUES (59.123, 11.567, 'TestSted', 'café', 'Fredrikstad', 'Sentrum');
    """);

        // Så oppretter vi viewFredrikstad
        stmt.execute("""
        CREATE VIEW viewFredrikstadPoI AS 
        SELECT * FROM poi WHERE city = 'Fredrikstad';
    """);

        conn.close();

        // Setter opp test-connector
        TestSQLConnector h2Connector = new TestSQLConnector(h2Url, "test", "test123");
        TestSQLdbConnect sqlDbConnect = new TestSQLdbConnect(h2Connector);

        sqlDbConnect.run(); // leser inn Fredrikstad-data

        // Oppretter et objekt av tilpasset Sightscontroller
        TestSightsController sightsController = new TestSightsController(sqlDbConnect);

        String userRequest = "\"Fredrikstad\"";

        // Så starter vi Javalin på tilfeldig port
        Javalin app = Javalin.create().start(0);
        app.post("/api/sights", sightsController::getSight);

        HttpClient client = HttpClient.newHttpClient();
        int port = app.port();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/api/sights"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userRequest))
                .build();

        // Act
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assert
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("Fredrikstad"));

        app.stop();
    }

}