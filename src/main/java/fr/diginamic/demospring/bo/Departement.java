package fr.diginamic.demospring.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.NavigableMap;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NOM")
    private String nom;

    @OneToMany (mappedBy = "departement")
    private List<Ville> villes;

    public Departement() {

    }

    public Departement(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", villes=" + villes +
                '}';
    }
}
