package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.DTO.VilleMapper;
import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;
import fr.diginamic.demospring.repositories.DepartementRepository;
import fr.diginamic.demospring.repositories.VilleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    private final String VILLE_NOT_FOUND = "Aucune ville trouvé.";
    private final String DEPARTEMENT_NOT_FOUND = "Aucun departement trouvé";
    private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository, DepartementRepository departementRepository) {
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
    }

    public ResponseEntity<?> extractAllVilles(int page, int size) {
        List<Ville> villes = villeRepository.findAll(PageRequest.of(page, size)).getContent();
        if(villes.isEmpty()) return ResponseEntity.badRequest().body(VILLE_NOT_FOUND);
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> extractVilles(Integer id, String nom, String codeDep, int min, Integer max) {
        List<Ville> villes;

        if(id != null) {
            Optional<Ville> ville = villeRepository.findById(id);
            if(ville.isPresent()) return ResponseEntity.ok().body(VilleMapper.toDto(ville.get()));
            else return ResponseEntity.badRequest().body(VILLE_NOT_FOUND);
        }

        if(nom != null && codeDep != null) {
            if(max == null) {
                villes = villeRepository.findByDepartementCodeAndNomStartingWithIgnoreCaseAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc( codeDep, nom, min);
            }
            else {
                villes = villeRepository.findByDepartementCodeAndNomStartingWithIgnoreCaseAndNbHabitantBetweenOrderByNbHabitantDesc(codeDep, nom, min, max);
            }
        }
        else if(nom != null) {
            if(max == null) {
                villes = villeRepository.findByNomStartingWithIgnoreCaseAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc(nom, min);
            }
            else {
                villes = villeRepository.findByNomStartingWithIgnoreCaseAndNbHabitantBetweenOrderByNbHabitantDesc(nom, min, max);
            }
        }
        else if(codeDep != null) {
            if(max == null) {
                villes = villeRepository.findByDepartementCodeAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc(codeDep, min);
            }
            else {
                villes = villeRepository.findByDepartementCodeAndNbHabitantBetweenOrderByNbHabitantDesc(codeDep, min, max);
            }
        }
        else{
            villes = max == null ? villeRepository.findByNbHabitantGreaterThanEqualOrderByNbHabitantDesc(min) : villeRepository.findByNbHabitantBetweenOrderByNbHabitantDesc(min, max);
        }

        if(villes.isEmpty()) return ResponseEntity.badRequest().body(VILLE_NOT_FOUND);
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> extractTopNVillesByDepartement(String codeDep, int n) {
        List<Ville> villes = villeRepository.findTopNDepartementCodeOrderByNbHabitantDesc(n, codeDep);

        if(villes.isEmpty()) return ResponseEntity.badRequest().body(VILLE_NOT_FOUND);
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> insertVille(VilleDTO villeDto, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());
        }
        List<Departement> departements = departementRepository.findByCode(villeDto.getCodeDep()); // ou findById
        if (departements.isEmpty()) return ResponseEntity.badRequest().body(DEPARTEMENT_NOT_FOUND);

        Ville ville = VilleMapper.toEntity(villeDto, departements.getFirst());
        villeRepository.save(ville);
        return ResponseEntity.ok().body(villeDto);
    }

    public ResponseEntity<?> supprimerVille(int id) {
        Optional<Ville> ville = villeRepository.findById(id);
        if(ville.isEmpty())return ResponseEntity.badRequest().body(VILLE_NOT_FOUND);

        villeRepository.delete(ville.get());
        return ResponseEntity.ok().body(VilleMapper.toDto(ville.get()));
    }
}
