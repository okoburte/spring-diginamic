package fr.diginamic.demospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("web")
public class DemoSpringApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","web");
        SpringApplication.run(DemoSpringApplication.class, args);
    }

}
