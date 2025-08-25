package fr.diginamic.demospring.repositories;

import fr.diginamic.demospring.bo.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    Optional<Ville> findById(int id);
    List<Ville> findByNbHabitantGreaterThanEqualOrderByNbHabitantDesc(int min);
    List<Ville> findByNbHabitantBetweenOrderByNbHabitantDesc(int min, int max);
    List<Ville> findByNomStartingWithIgnoreCaseAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc(String nom, int min);
    List<Ville> findByNomStartingWithIgnoreCaseAndNbHabitantBetweenOrderByNbHabitantDesc(String nom, int min, int max);
    List<Ville> findByDepartementCodeAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc(String code, int min);
    List<Ville> findByDepartementCodeAndNbHabitantBetweenOrderByNbHabitantDesc(String code, int min, int max);
    List<Ville> findByDepartementCodeAndNomStartingWithIgnoreCaseAndNbHabitantGreaterThanEqualOrderByNbHabitantDesc(String code,String nom, int min);
    List<Ville> findByDepartementCodeAndNomStartingWithIgnoreCaseAndNbHabitantBetweenOrderByNbHabitantDesc(String code, String nom, int min, int max);
    @Query("SELECT v FROM Ville v WHERE v.departement.code = :code ORDER BY v.nbHabitant DESC LIMIT :n")
    List<Ville> findTopNDepartementCodeOrderByNbHabitantDesc(int n, String code);
}

