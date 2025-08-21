package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.bo.Ville;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    List<Ville> villes = new ArrayList<>();

    @GetMapping
    public List<Ville> getVilles() {
        villes.add(new Ville("St Gely", 10500));
        villes.add(new Ville("Montpellier", 105000));
        return villes;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getVille(@PathVariable("id") int id) {
        Ville v = villes.stream().filter(ville->ville.getId() == id).findFirst().get();
        if (v == null) {
            return ResponseEntity.badRequest().body("erreur2");
        }
        return ResponseEntity.ok().body(v);
    }

    @PostMapping
    public ResponseEntity<?> addVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        if(villes.stream().anyMatch(v -> v.getNom().equals(ville.getNom()))) {
            return ResponseEntity.badRequest().body("Ville existe.");
        }
        villes.add(ville);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateVille(@PathVariable("id") int id, @Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        if (villes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Liste en mémoire non initialisée.");
        }

        // Cherche en une seule passe
        Optional<Ville> opt = villes.stream()
                .filter(v -> v.getId() == id)
                .findFirst();

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ville non trouvée.");
        }

        Ville v = opt.get();
        v.setNom(ville.getNom());
        v.setNbHabitant(ville.getNbHabitant());

        return ResponseEntity.ok(v);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVille(@PathVariable("id") int id) {
        Optional<Ville> opt = villes.stream()
                .filter(v -> v.getId() == id)
                .findFirst();

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ville non trouvée.");
        }

        Ville v = opt.get();
        villes.remove(v);
        return ResponseEntity.ok(v);
    }
}
