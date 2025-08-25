package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.services.DepartementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    private DepartementService departementService;

    public DepartementControleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public ResponseEntity<?> getDepartement(@RequestParam(required = false) String code, @RequestParam(required = false) String nom) {
        return departementService.extractDepartements(code, nom);
    }

    @PostMapping
    public ResponseEntity<?> addDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        return departementService.insertDepartement(departementDTO, result);
    }

    @PutMapping
    public ResponseEntity<?> updateDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        return departementService.insertDepartement(departementDTO, result);
    }

    @DeleteMapping(path = "/{code}")
    public ResponseEntity<?> deleteDepartement(@PathVariable("code") String code) {
        return departementService.supprimerDepartement(code);
    }
}
