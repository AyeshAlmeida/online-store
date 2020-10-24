package org.sample.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServer.class);

    public static void main(String... args) {
        final long start = System.currentTimeMillis();
        SpringApplication.run(ApiServer.class, args);
        String startupTime = String.valueOf(System.currentTimeMillis() - start);
        System.out.println("###################################################################");
        System.out.println("##              API-Server Started Successfully                  ##");
        System.out.println("##              Time Taken: " + String.format("%1$-" + 37 + "s", startupTime) + "##");
        System.out.println("###################################################################");
        LOGGER.info("API-Server Started Successfully.");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("###################################################################");
                System.out.println("##              API-Server Stopped Successfully                  ##");
                System.out.println("###################################################################");
            }
        });
    }
}
