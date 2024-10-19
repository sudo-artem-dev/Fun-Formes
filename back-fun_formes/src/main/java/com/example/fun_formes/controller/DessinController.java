package com.example.fun_formes.controller;

import com.example.fun_formes.dto.*;
import com.example.fun_formes.model.Dessin;
import com.example.fun_formes.model.Form;
import com.example.fun_formes.service.DessinService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dessins")
public class DessinController {

    private final DessinService dessinService;

    @Autowired // Injecte automatiquement le service DessinService dans le controller
    public DessinController(DessinService dessinService) {
        this.dessinService = dessinService;
    }
    //GET
    @GetMapping
    public ResponseEntity<List<Dessin>> getAllDessins() {
        List<Dessin> dessins = dessinService.getAllDessins();
        if (dessins.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 not found
        }
        return ResponseEntity.ok(dessins); // 200 OK
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Dessin> getDessinById(@PathVariable long id) {
        Dessin dessin = dessinService.getDessinById(id);
        if (dessin != null) {
            return ResponseEntity.ok(dessin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/formes")
    public ResponseEntity<List<Form>> getFormesFromDessin(@PathVariable Long id) {
        List<Form> formes = dessinService.getFormesFromDessin(id);
        if (formes != null) {
            return ResponseEntity.ok(formes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // GET pour récupérer les dessins par niveau
    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<DessinParNiveauDTO> getDessinsByNiveau(@PathVariable String niveau) {
        List<Dessin> dessins = dessinService.getDessinsByNiveau(niveau);
        
        if (dessins.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        //  Transformation des objets Dessin en DessinDTO
        List<DessinDTO> dessinDTOList = dessins.stream()
                .map(dessin -> new DessinDTO(
                        dessin.getId(),
                        dessin.getNom(),
                        dessin.getFormes().size(),
                        dessin.compterFigures(),
                        dessin.getFormes(),
                        dessin.calculerPerimetreTotal(),
                        dessin.calculerAireTotal()))
                .toList();

            DessinParNiveauDTO response = new DessinParNiveauDTO(niveau, dessinDTOList);
        
        return ResponseEntity.ok(response);
    }
    // GET pour récupérer un dessin par niveau et par ID
    @GetMapping("/niveau/{niveau}/{id}")
    public ResponseEntity<DessinDTO> getDessinByNiveauAndId(@PathVariable String niveau, @PathVariable Long id) {
        // Récupérer le dessin en fonction du niveau et de l'ID
        Dessin dessin = dessinService.getDessinByNiveauAndId(niveau, id);
        
        if (dessin == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Transformation de l'objet Dessin en DessinDTO
        DessinDTO dessinDTO = new DessinDTO(
                dessin.getId(),
                dessin.getNom(),
                dessin.getFormes().size(),
                dessin.compterFigures(),
                dessin.getFormes(),
                dessin.calculerPerimetreTotal(),
                dessin.calculerAireTotal());
        
        return ResponseEntity.ok(dessinDTO);
    }

    //POST
    @PostMapping
    public ResponseEntity<Dessin> createDessin(@RequestBody Dessin dessin) {
        Dessin createdDessin = dessinService.createDessin(dessin);
        return ResponseEntity.ok(createdDessin);  // retourne un statut 200 (OK) avec le corps
    }

    @PostMapping("/{id}/formes")
    public ResponseEntity<Void> addFormeToDessin(@PathVariable Long id, @RequestBody Form forme) {
        dessinService.addFormeToDessin(id, forme);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Dessin> updateDessin(@PathVariable long id, @RequestBody Dessin dessin) {
        Dessin updatedDessin = dessinService.updateDessin(id, dessin);
        if (updatedDessin != null) {
            return ResponseEntity.ok(updatedDessin); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDessin(@PathVariable Long id) {
        try {
            dessinService.supprimerDessin(id);
            return ResponseEntity.noContent().build(); // retourne un statut 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // retourne un statut 404 Not Found
        }
    }
}
