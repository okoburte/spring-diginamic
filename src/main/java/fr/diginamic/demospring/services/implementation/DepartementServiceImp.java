package fr.diginamic.demospring.services.implementation;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.DTO.DepartementMapper;
import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import fr.diginamic.demospring.repositories.DepartementRepository;
import fr.diginamic.demospring.services.DepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class DepartementServiceImp implements DepartementService {
    private final DepartementRepository departementRepository;

    public DepartementServiceImp(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public ResponseEntity<?> extractDepartements(String code, String nom) throws ExceptionElement {
        List<Departement> departements;
        if(code != null){
            departements = departementRepository.findByCode(code);
        }
        else if(nom != null){
            departements = departementRepository.findByNomStartingWithIgnoreCase(nom);
        }
        else departements = departementRepository.findBy();

        if(departements.isEmpty()) {
            throw new ExceptionElement("Aucun departement trouvée pour " + (code != null ? "code = " + code : "") + "." + (nom != null ? "nom = " + nom + "." : ""));
        }
        return ResponseEntity.ok().body(DepartementMapper.toDtos(departements));
    }

    @Override
    public ResponseEntity<?> insertDepartement(DepartementDTO departementDTO) throws ExceptionElement {
        List<Departement> departements = departementRepository.findByCode(departementDTO.getCode());
        if(departements.isEmpty()) {
            departements.add(DepartementMapper.toEntity(departementDTO));
            departementRepository.saveAll(departements);
        }
        else {
            departements.get(0).setNom(departementDTO.getNom());
        }
        return ResponseEntity.ok().body(departementDTO);
    }

    @Override
    public ResponseEntity<?> supprimerDepartement(String code) throws ExceptionElement {
        List<Departement> departements = departementRepository.findByCode(code);
        if(departements.isEmpty()) throw new ExceptionElement("Aucun departement trouvée pour le code " + code + ".");
        departementRepository.deleteAll(departements);
        return ResponseEntity.ok().body(code);
    }
}
