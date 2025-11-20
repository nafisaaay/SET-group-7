package no.hiof.setgroup7;

import io.javalin.Javalin;
import no.hiof.setgroup7.DTOs.PoiDTO;
import no.hiof.setgroup7.controller.SightsController;
import no.hiof.setgroup7.controller.TripController;
import no.hiof.setgroup7.database.SQLdbConnect;
import no.hiof.setgroup7.integration.EnturClient;
import no.hiof.setgroup7.repository.SQLConnector;
import no.hiof.setgroup7.repository.SQLProcedures;
import no.hiof.setgroup7.service.TripService;
import no.hiof.setgroup7.ticketsys.model.Customer;
import no.hiof.setgroup7.ticketsys.service.TicketService;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) {
        Javalin httpsApp = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(rule -> rule.anyHost()));

            config.jetty.modifyServer(server -> {
                SslContextFactory.Server sslContextFactory = getSslContextFactory();

                HttpConfiguration httpsConfig = new HttpConfiguration();
                httpsConfig.setSecureScheme("https");
                httpsConfig.setSecurePort(8443);
                httpsConfig.setSendServerVersion(false);

                // Skrur av SNI check i nettleser
                SecureRequestCustomizer secureRequestCustomizer = new SecureRequestCustomizer();
                secureRequestCustomizer.setSniHostCheck(false);
                httpsConfig.addCustomizer(secureRequestCustomizer);

                SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(
                        sslContextFactory,
                        "http/1.1"
                );

                HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpsConfig);

                ServerConnector sslConnector = new ServerConnector(
                        server,
                        sslConnectionFactory,
                        httpConnectionFactory
                );
                sslConnector.setPort(8443);

                server.addConnector(sslConnector);
            });
        }).start(8443); // port for https

        // vanlig port uten https
        Javalin httpApp = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors ->
                    cors.addRule(rule -> rule.anyHost()));
        }).start(8000);

        SQLdbConnect sqLdbConnect = new SQLdbConnect();
        sqLdbConnect.run();

        EnturClient client = new EnturClient();
        TripService tripService = new TripService(client);
        Customer customer = new Customer();
        TicketService ticketService = new TicketService();
        TripController tripController = new TripController(tripService, customer, ticketService);

        SightsController sightsController = new SightsController(sqLdbConnect);


        for (Javalin app : new Javalin[]{httpsApp}) {
            //app.post("/api/trip", context -> tripController.getTripFormData(context));
            app.get("/health", context -> context.result("ok"));
            app.post("/api/trip", context -> {
                        tripController.getTripFormData(context);
                        app.post("api/sights", context1 -> sightsController.getSight(context));
                        app.before(ctx -> {
                            if (!"https".equalsIgnoreCase(ctx.scheme())) {
                                String redirect = "https://localhost:8443" + ctx.path();
                                ctx.redirect(redirect);
                            }
                        });
                    });

            System.out.println(" HTTP on http://localhost:8000");
            System.out.println(" HTTPS on https://localhost:8443/api/trip");
        }
    }

    private static SslContextFactory.Server getSslContextFactory() {
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath("backend/keystore.p12");
        sslContextFactory.setKeyStorePassword("filippos036");
        sslContextFactory.setSniRequired(false);
        return sslContextFactory;
    }


}


