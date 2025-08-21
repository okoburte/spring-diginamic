package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DAO.DAODepartement;
import fr.diginamic.demospring.DAO.DAOVille;
import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.beans.Transient;
import java.util.List;

@Service
public class VilleService {
    private final DAODepartement daoDepartement;
    private final DAOVille daoVille;

    public VilleService(DAOVille daoVille, DAODepartement daoDepartement) {
        this.daoVille = daoVille;
        this.daoDepartement = daoDepartement;
    }

    public List<Ville> extractVilles() {
        return daoVille.extractVilles();
    }

    public Ville extractVille(int id) {
        Ville ville = daoVille.extractVille(id);
        return ville;
    }

    public Ville extractVille(String nom) {
        Ville ville = daoVille.extractVille(nom);
        return ville;
    }

    @Transactional
    public List<Ville> insertVille(int idDep, Ville ville) {
        // Charge un Departement MANAGÉ (getReference évite une requête si juste FK)
        Departement dep = daoDepartement.extractDepartement(idDep); // ou findById
        if (dep == null) return null;

        Ville v = new Ville();
        v.setNom(ville.getNom());
        v.setNbHabitant(ville.getNbHabitant());
        // lie proprement les deux côtés via helpers
        dep.addVille(v);

        return daoVille.insertVille(v);
    }

    public List<Ville> modifierVille(int idDep, int idVille, Ville ville) {
        List<Ville> villes = daoVille.modifierVille(idVille, ville);
        return villes;
    }

    public List<Ville> supprimerVille(int idVille) {
        List<Ville> villes = daoVille.supprimerVille(idVille);
        return villes;
    }
}
