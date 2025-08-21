package fr.diginamic.demospring.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class Ville {
    private int id;
    @NotNull
    @Size(min=2, max=50, message = "Le nom doit comporter au moins 2 caractere.")
    private String nom;
    @Range(min=1, message = "La population doit etre superieur a 0.")
    private Integer nbHabitant;

    private static int counter = 1;

    public Ville(String nom, Integer nbHabitant) {
        this.id = counter++;
        this.nom = nom;
        this.nbHabitant = nbHabitant;
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

    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", nbHabitant=" + nbHabitant +
                '}';
    }
}
