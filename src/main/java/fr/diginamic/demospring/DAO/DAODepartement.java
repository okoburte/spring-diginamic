package fr.diginamic.demospring.DAO;

import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class DAODepartement {
    @PersistenceContext
    private EntityManager em;

    public List<Departement> extractDepartements() {
        TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
        return query.getResultList();
    }

    public List<Departement> insertDepartement(Departement departement) {
        em.persist(departement);
        return extractDepartements();
    }

    public List<Departement> modifierDepartement(int id, Departement departement) {
        Departement d = em.find(Departement.class, id);
        d.setNom(departement.getNom());
        return extractDepartements();
    }


    public List<Departement> supprimerDepartement(int id) {
        em.remove(em.find(Departement.class, id));
        return extractDepartements();
    }

    public Departement extractDepartement(int idDep) {
        return em.find(Departement.class, idDep);
    }
}
