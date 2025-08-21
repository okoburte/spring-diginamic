package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.services.DepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    private DepartementService departementService;

    public DepartementControleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public List<Departement> getDepartements() {
        return departementService.extractDepartements();
    }

    @PostMapping
    public ResponseEntity<?> addDepartement(@RequestBody Departement departement) {
        departementService.insertDepartement(departement);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateDepartement(@PathVariable int id , @RequestBody Departement departement) {
        departementService.modifierDepartement(id, departement);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteDepartement(@PathVariable int id) {
        departementService.supprimerDepartement(id);
        return ResponseEntity.ok().build();
    }
}
