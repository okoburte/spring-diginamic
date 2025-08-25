package fr.diginamic.demospring.DTO;

import fr.diginamic.demospring.bo.Departement;

import java.util.List;

public class DepartementMapper {
    public static DepartementDTO toDto(Departement departement) {
        if(departement == null) return null;
        return new DepartementDTO(departement);
    }

    public static List<DepartementDTO> toDtos(List<Departement> departements) {
        return departements.stream().map(DepartementMapper::toDto).toList();
    }

    public static Departement toEntity(DepartementDTO departementDTO) {
        return new Departement(departementDTO.getNom(), departementDTO.getCode());
    }
}
