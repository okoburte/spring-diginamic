package fr.diginamic.demospring.DTO;

import fr.diginamic.demospring.bo.Departement;
import fr.diginamic.demospring.bo.Ville;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VilleMapper {
    public static VilleDTO toDto(Ville ville) {
        if(ville == null) return null;
        return new VilleDTO(ville.getId(), ville.getNom(), ville.getNbHabitant(), ville.getDepartement().getCode());
    }

    public static List<VilleDTO> toDtos(List<Ville> villes) {
        return villes.stream().map(VilleMapper::toDto).toList();
    }

    public static Ville toEntity(VilleDTO villeDto, Departement departement) {
        return new Ville(villeDto.getNom(), villeDto.getNbHabitant(), departement);
    }
}
