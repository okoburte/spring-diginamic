package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface VilleService {
    ResponseEntity<?> extractAllVilles(int page, int size) throws ExceptionElement;

    ResponseEntity<?> extractVilles(Integer id, String nom, String codeDep, int min, Integer max) throws ExceptionElement;

    ResponseEntity<?> extractTopNVillesByDepartement(String codeDep, int n) throws ExceptionElement;

    ResponseEntity<?> insertVille(VilleDTO villeDto, BindingResult result) throws ExceptionElement;

    ResponseEntity<?> supprimerVille(int id) throws ExceptionElement;
}
