package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    private final VilleService villeService;

    public VilleControleur(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public ResponseEntity<?> getVilles(@RequestParam(required = false) Integer id, @RequestParam(required = false) String nom, @RequestParam(required = false) String codeDep, @RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if (min == null) min = 0;

        return villeService.extractVilles(id, nom, codeDep, min, max);
    }

    @GetMapping(path = "/{codeDep}")
    public ResponseEntity<?> getTopVilles(@PathVariable("codeDep") String codeDep, @RequestParam(required = false) Integer n) {
        if (n == null) n = 10;

        return villeService.extractTopNVillesByDepartement(codeDep, n);
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<?> getAllVilles(@RequestParam int page, @RequestParam int size){
        return villeService.extractAllVilles(page, size);
    }

    @PostMapping
    public ResponseEntity<?> addVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        return villeService.insertVille(villeDto, result);
    }

    @PutMapping
    public ResponseEntity<?> updateVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        return villeService.insertVille(villeDto, result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVille(@PathVariable("id") int id) {
        return villeService.supprimerVille(id);
    }
}
