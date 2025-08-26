package fr.diginamic.demospring.DTO;

import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;

import java.util.List;

public class DepartementDTO{
    private String code;
    private String nom;
    private String codeRegion;
    private List<String> nomVilles;

    public DepartementDTO() {

    }

    public DepartementDTO(String nom, String code, List<String> nomVilles) {
        this.code = code;
        this.nom = nom;
        this.nomVilles = nomVilles;
    }

    public DepartementDTO(Departement departement){
        this.code = departement.getCode();
        this.nom = departement.getNom();
        this.nomVilles = departement.getVilles().stream().map(Ville::getNom).toList();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getNomVilles() {
        return nomVilles;
    }

    public void setNomVilles(List<String> nomVilles) {
        this.nomVilles = nomVilles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DepartementDTO{" +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                ", idVilles=" + nomVilles +
                '}';
    }
}
