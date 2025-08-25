package fr.diginamic.demospring.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Entity
@Table(name = "ville")
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOM")
    @NotNull
    @Size(min=2, max=50, message = "Le nom doit comporter au moins 2 caractere.")
    private String nom;
    @Column(name = "NB_HABS")
    @Range(min=1, message = "La population doit etre superieur a 0.")
    private Integer nbHabitant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_DEPT", referencedColumnName = "ID")
    private Departement departement;

    public Ville() {
    }

    public Ville(String nom, Integer nbHabitant, Departement departement) {
        this.nom = nom;
        this.nbHabitant = nbHabitant;
        this.departement = departement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNbHabitant() {
        return nbHabitant;
    }

    public void setNbHabitant(Integer nbHabitant) {
        this.nbHabitant = nbHabitant;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbHabitant=" + nbHabitant +
                ", departement=" + departement +
                '}';
    }
}
