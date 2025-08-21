package fr.diginamic.demospring.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.NavigableMap;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NOM")
    private String nom;

    @OneToMany (mappedBy = "departement")
    private List<Ville> villes;

    public Departement() {

    }

    public Departement(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public void addVille(Ville ville) {
        this.villes.add(ville);
    }

    public void removeVille(Ville ville) {
        this.villes.remove(ville);
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", villes=" + villes +
                '}';
    }
}
