package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.bo.Ville;
import fr.diginamic.demospring.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    private final VilleService villeService;

    public VilleControleur(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public List<Ville> getVilles() {
        return villeService.extractVilles();
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<?> getVille(@PathVariable("id") int id) {
        return ResponseEntity.ok(villeService.extractVille(id));
    }

    @GetMapping(path = "/nom/{nom}")
    public ResponseEntity<?> getVille(@PathVariable("nom") String nom) {
        return ResponseEntity.ok(villeService.extractVille(nom));
    }

    @PostMapping(path = "/{idDep}")
    public ResponseEntity<?> addVille(@PathVariable("idDep") int idDep, @Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        villeService.insertVille(idDep, ville);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{idDep}/{id}")
    public ResponseEntity<?> updateVille(@PathVariable("idDep") int idDep, @PathVariable("id") int id, @RequestBody Ville ville) {
        villeService.modifierVille(idDep, id, ville);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVille(@PathVariable("id") int id) {
        villeService.supprimerVille(id);
        return ResponseEntity.ok().build();
    }
}
