package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.DTO.VilleMapper;
import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;
import fr.diginamic.demospring.exceptions.ExceptionElement;
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
    private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository, DepartementRepository departementRepository) {
        this.villeRepository = villeRepository;
        this.departementRepository = departementRepository;
    }

    public ResponseEntity<?> extractAllVilles(int page, int size) throws ExceptionElement {
        List<Ville> villes = villeRepository.findAll(PageRequest.of(page, size)).getContent();
        if(villes.isEmpty()) throw new ExceptionElement("Aucune ville trouvé dans la base de donnée en page " + page + " (taille des pages : " + size + ").");
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> extractVilles(Integer id, String nom, String codeDep, int min, Integer max) throws ExceptionElement {
        List<Ville> villes;

        if(id != null) {
            Optional<Ville> ville = villeRepository.findById(id);
            if(ville.isPresent()) return ResponseEntity.ok().body(VilleMapper.toDto(ville.get()));
            else throw new ExceptionElement("La ville d'id " + id + " n'existe pas dans la base de donnée.");
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

        if(villes.isEmpty()) throw new ExceptionElement("Aucune ville dans la base de donnée correspond a :" + (nom != null ? " nom = " + nom + "." : "") + (codeDep != null ? " codeDep = " + codeDep + "." : "")
                            + (min != 0 ? " population min = " + min + "." : "") + (max != null ? " population max = " + max + "." : ""));
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> extractTopNVillesByDepartement(String codeDep, int n) throws ExceptionElement {
        List<Ville> villes = villeRepository.findTopNDepartementCodeOrderByNbHabitantDesc(n, codeDep);

        if(villes.isEmpty()) throw new ExceptionElement("Aucune ville trouvée pour le code departement " + codeDep + ".");
        return ResponseEntity.ok().body(VilleMapper.toDtos(villes));
    }

    public ResponseEntity<?> insertVille(VilleDTO villeDto, BindingResult result) throws ExceptionElement {
        if(result.hasErrors()){
            throw new ExceptionElement(result.getAllErrors().getFirst().getDefaultMessage());
        }
        List<Departement> departements = departementRepository.findByCode(villeDto.getCodeDep()); // ou findById
        if (departements.isEmpty()) throw new ExceptionElement("Aucun departement trouvé pour le code " + villeDto.getCodeDep() + ".");

        Ville ville = VilleMapper.toEntity(villeDto, departements.getFirst());
        villeRepository.save(ville);
        return ResponseEntity.ok().body(villeDto);
    }

    public ResponseEntity<?> supprimerVille(int id) throws ExceptionElement {
        Optional<Ville> ville = villeRepository.findById(id);
        if(ville.isEmpty()) throw new ExceptionElement("La ville d'id " + id + " n'existe pas dans la base de donnée.");

        villeRepository.delete(ville.get());
        return ResponseEntity.ok().body(VilleMapper.toDto(ville.get()));
    }
}
