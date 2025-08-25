package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import fr.diginamic.demospring.services.DepartementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    private final DepartementService departementService;

    public DepartementControleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public ResponseEntity<?> getDepartement(@RequestParam(required = false) String code, @RequestParam(required = false) String nom) {
        try {
            return departementService.extractDepartements(code, nom);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        try {
            return departementService.insertDepartement(departementDTO, result);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        try {
            return departementService.insertDepartement(departementDTO, result);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{code}")
    public ResponseEntity<?> deleteDepartement(@PathVariable("code") String code) {
        try {
            return departementService.supprimerDepartement(code);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
