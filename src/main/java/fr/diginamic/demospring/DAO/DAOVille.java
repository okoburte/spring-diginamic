package fr.diginamic.demospring.DAO;

import fr.diginamic.demospring.bo.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@Transactional
public class DAOVille {
    @PersistenceContext
    private EntityManager em;

    public List<Ville> extractVilles() {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v", Ville.class);
        return query.getResultList();
    }

    public Ville extractVille(int id) {
        return em.find(Ville.class, id);
    }

    public  Ville extractVille(String nom) {
        TypedQuery<Ville> query = em.createQuery("SELECT v FROM Ville v where v.nom = :nom", Ville.class);
        return query.setParameter("nom", nom).getResultStream().findFirst().orElse(null);
    }

    public List<Ville> insertVille(Ville ville) {
        // ici, ville.getDepartement() doit déjà être MANAGÉ, car mis par le service
        em.persist(ville);
        return extractVilles();
    }

    public List<Ville> modifierVille(int idVille, Ville ville) {
        Ville v = em.find(Ville.class, idVille);
        if (v != null) {
            v.setNom(ville.getNom());
            v.setNbHabitant(ville.getNbHabitant());
            if(!ville.getDepartement().equals(v.getDepartement())){
                v.getDepartement().removeVille(v);
                v.setDepartement(ville.getDepartement());
            }
        }
        return extractVilles();
    }

    public List<Ville> supprimerVille(int idVille) {
        Ville v = em.find(Ville.class, idVille);
        if(v != null){
            v.getDepartement().removeVille(v);
            em.remove(v);
        }
        return extractVilles();
    }
}
