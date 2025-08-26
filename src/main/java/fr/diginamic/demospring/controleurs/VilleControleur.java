package fr.diginamic.demospring.controleurs;

import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import fr.diginamic.demospring.services.VilleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.hibernate.annotations.Array;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    private final VilleService villeService;

    public VilleControleur(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    @Operation(summary = "Retourne la liste des villes. peut prendre different paramettre de filtrage.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
                            description = "Liste des villes au format JSON",
                            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))})})
    public ResponseEntity<?> getVilles(@RequestParam(required = false) Integer id, @RequestParam(required = false) String nom, @RequestParam(required = false) String codeDep, @RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if (min == null) min = 0;

        try {
            return villeService.extractVilles(id, nom, codeDep, min, max);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{codeDep}")
    @Operation(summary = "Retourne les n villes les plus peuplé d'un departement donné.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
                            description = "Liste des villes au format JSON",
                            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))}),
                        @ApiResponse(responseCode = "400",
                            description = "Ville non trouvé",
                            content = @Content())})
    public ResponseEntity<?> getTopVilles(@PathVariable("codeDep") String codeDep, @RequestParam(required = false) Integer n) {
        if (n == null) n = 10;

        try {
            return villeService.extractTopNVillesByDepartement(codeDep, n);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/pagination")
    @Operation(summary = "Retourne les n villes de la page x dans la base de donnée")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Liste des villes au format JSON",
            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Ville non trouvé a la page donnée",
                    content = @Content())})
    public ResponseEntity<?> getAllVilles(@RequestParam int page, @RequestParam int size){
        try {
            return villeService.extractAllVilles(page, size);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Ajoute une ville dans la base de donnée")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "ville au format JSON",
            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Ville non conforme",
                    content = @Content())})
    public ResponseEntity<?> addVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        try {
            return villeService.insertVille(villeDto, result);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Operation(summary = "Update une ville dans la base de donnée")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "ville au format JSON",
            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Ville non conforme",
                    content = @Content())})
    public ResponseEntity<?> updateVille(@Valid @RequestBody VilleDTO villeDto, BindingResult result) {
        try {
            return villeService.insertVille(villeDto, result);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Supprime une ville dans la base de donnée")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "ville au format JSON",
            content = {@Content(mediaType="application/json", array=@ArraySchema(schema = @Schema(implementation = VilleDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Ville non trouvée",
                    content = @Content())})
    public ResponseEntity<?> deleteVille(@PathVariable("id") int id) {
        try {
            return villeService.supprimerVille(id);
        } catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
