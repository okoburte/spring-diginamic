package fr.diginamic.demospring.DTO;

import jakarta.validation.constraints.NotNull;

public class VilleDTO{
    private int id;
    private String nom;
    private Integer nbHabitant;
    @NotNull
    private String codeDep;

    public VilleDTO() {
    }

    public VilleDTO(String nom, Integer nbHabitant, String codeDep) {
        this.nom = nom;
        this.nbHabitant = nbHabitant;
        this.codeDep = codeDep;
    }

    public VilleDTO(int id, String nom, Integer nbHabitant, String code) {
        this(nom, nbHabitant, code);
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

    public String getCodeDep() {
        return codeDep;
    }

    public void setCodeDep(String codeDep) {
        this.codeDep = codeDep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VilleDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbHabitant=" + nbHabitant +
                ", codeDep='" + codeDep + '\'' +
                '}';
    }
}
