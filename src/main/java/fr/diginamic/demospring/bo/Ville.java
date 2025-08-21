package fr.diginamic.demospring.bo;

public class Ville {
    private int id;
    private String nom;
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
