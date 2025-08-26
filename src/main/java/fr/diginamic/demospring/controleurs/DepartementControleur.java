package fr.diginamic.demospring.controleurs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import fr.diginamic.demospring.DTO.DepartementDTO;
import fr.diginamic.demospring.DTO.VilleDTO;
import fr.diginamic.demospring.exceptions.ExceptionElement;
import fr.diginamic.demospring.services.DepartementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {
    private final DepartementService departementService;

    public DepartementControleur(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public ResponseEntity<?> getDepartement(@RequestParam(required = false) String code, @RequestParam(required = false) String nom) {
        try {
            return departementService.extractDepartements(code, nom);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/export")
    @Operation(summary = "Retourne le departement en fonction de son code sous format pdf")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Departement en export pdf",
            content = {@Content(mediaType="fichier pdf", array=@ArraySchema(schema = @Schema(implementation = DepartementDTO.class)))}),
            @ApiResponse(responseCode = "400",
                    description = "Departement non trouvée",
                    content = @Content())})
    public void exportDepartement(@RequestParam String code, HttpServletResponse response) throws IOException, DocumentException, ExceptionElement {
        List<DepartementDTO> departementDtos = (List<DepartementDTO>) departementService.extractDepartements(code, null).getBody();

        response.setHeader("Content-Disposition", "attachment; filename=\"departement.pdf\"");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.addTitle("Departements");
        document.newPage();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        departementDtos.stream().forEach(d -> {
            StringBuffer stringContent = new StringBuffer();
            stringContent.append("Code Departement : ").append(d.getCode()).append("\nNom Departement : ").append(d.getNom()).append("\n");
            stringContent.append("Villes du departement : \n");
            d.getNomVilles().stream().forEach(v -> stringContent.append("- ").append(v).append("\n"));
            Phrase p = new Phrase(String.valueOf(stringContent), new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
            try {
                document.add(p);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        document.close();
    }

    @PostMapping
    public ResponseEntity<?> addDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());
        }
        try {
            return departementService.insertDepartement(departementDTO);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDepartement(@Valid @RequestBody DepartementDTO departementDTO, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors().getFirst().getDefaultMessage());
        }
        try {
            return departementService.insertDepartement(departementDTO);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{code}")
    public ResponseEntity<?> deleteDepartement(@PathVariable("code") String code) {
        try {
            return departementService.supprimerDepartement(code);
        }
        catch (ExceptionElement e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
