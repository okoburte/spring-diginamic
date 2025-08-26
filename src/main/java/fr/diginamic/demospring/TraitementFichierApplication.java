package fr.diginamic.demospring;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import fr.diginamic.demospring.services.DepartementService;
import fr.diginamic.demospring.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@Profile("traitement")
public class TraitementFichierApplication implements CommandLineRunner {
    @Autowired
    private DepartementService departementService;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","traitement");
        SpringApplication app = new SpringApplication(TraitementFichierApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate= new RestTemplate();
        DepartementDTO[]dtoArray= restTemplate.getForObject("https://geo.api.gouv.fr/departements", DepartementDTO[].class);
        Arrays.stream(dtoArray).forEach(dto -> {
            try {
                departementService.insertDepartement(dto);
            } catch (ExceptionElement e) {
                throw new RuntimeException(e);
            }
        });
    }
}
