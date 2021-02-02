package by.itninjas.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry point to the application <b>Watch dogs</b>.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"by.itninjas"})
public class WatchDogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchDogsApplication.class, args);
    }

}
