package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.bo.Ville;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("villes")
public class VilleControleur {

    @GetMapping
    public List<Ville> getVilles() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("St Gely", 10500));
        villes.add(new Ville("Montpellier", 105000));
        return villes;
    }
}
