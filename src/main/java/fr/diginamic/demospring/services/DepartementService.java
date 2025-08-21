package fr.diginamic.demospring.services;

import fr.diginamic.demospring.DAO.DAODepartement;
import fr.diginamic.demospring.bo.Departement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {
    private DAODepartement daoDepartement;

    public DepartementService(DAODepartement daoDepartement) {
        this.daoDepartement = daoDepartement;
    }

    public List<Departement> extractDepartements() {
        List<Departement> departements = daoDepartement.extractDepartements();
        return departements;
    }

    public List<Departement> insertDepartement(Departement departement) {
        List<Departement> departements = daoDepartement.insertDepartement(departement);
        return departements;
    }

    public List<Departement> modifierDepartement(int id, Departement departement) {
        List<Departement> departements = daoDepartement.modifierDepartement(id, departement);
        return departements;
    }

    public List<Departement> supprimerDepartement(int id) {
        List<Departement> departements = daoDepartement.supprimerDepartement(id);
        return departements;
    }
}
