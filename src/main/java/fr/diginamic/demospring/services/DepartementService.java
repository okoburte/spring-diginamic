package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface DepartementService {
    ResponseEntity<?> extractDepartements(String code, String nom) throws ExceptionElement;

    ResponseEntity<?> insertDepartement(DepartementDTO departementDTO, BindingResult result) throws ExceptionElement;

    ResponseEntity<?> supprimerDepartement(String code) throws ExceptionElement;
}
