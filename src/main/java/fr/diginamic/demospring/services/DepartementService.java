package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.DTO.DepartementMapper;
import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.repositories.DepartementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class DepartementService {
    private final String DEPARTEMENT_NOT_FOUND = "Departement non trouvé";
    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public ResponseEntity<?> extractDepartements(String code, String nom) {
        List<Departement> departements;
        if(code != null){
            departements = departementRepository.findByCode(code);
        }
        else if(nom != null){
            departements = departementRepository.findByNomStartingWithIgnoreCase(nom);
        }
        else departements = departementRepository.findBy();

        if(departements.isEmpty()) {
            return ResponseEntity.badRequest().body(DEPARTEMENT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(DepartementMapper.toDtos(departements));
    }

    public ResponseEntity<?> insertDepartement(DepartementDTO departementDTO, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        Departement departement = DepartementMapper.toEntity(departementDTO);
        departementRepository.save(departement);
        return ResponseEntity.ok().body(departementDTO);
    }

    public ResponseEntity<?> supprimerDepartement(String code) {
        List<Departement> departements = departementRepository.findByCode(code);
        if(departements.isEmpty())return ResponseEntity.badRequest().body(DEPARTEMENT_NOT_FOUND);
        departementRepository.deleteAll(departements);
        return ResponseEntity.ok().body(code);
    }
}
