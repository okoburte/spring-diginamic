package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
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

        try {
            return villeService.extractVilles(id, nom, codeDep, min, max);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{codeDep}")
    public ResponseEntity<?> getTopVilles(@PathVariable("codeDep") String codeDep, @RequestParam(required = false) Integer n) {
        if (n == null) n = 10;

        try {
            return villeService.extractTopNVillesByDepartement(codeDep, n);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<?> getAllVilles(@RequestParam int page, @RequestParam int size){
        try {
            return villeService.extractAllVilles(page, size);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        try {
            return villeService.insertVille(villeDto, result);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        try {
            return villeService.insertVille(villeDto, result);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVille(@PathVariable("id") int id) {
        try {
            return villeService.supprimerVille(id);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
