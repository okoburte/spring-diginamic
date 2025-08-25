package fr.diginamic.demospring.repositories;

import fr.diginamic.demospring.bo.Departement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartementRepository extends CrudRepository <Departement, Integer>{
    List<Departement> findBy();
    List<Departement> findByCode(String code);
    List<Departement> findByNomStartingWithIgnoreCase(String nom);
}
