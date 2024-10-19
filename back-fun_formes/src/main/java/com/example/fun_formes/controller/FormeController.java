package com.example.fun_formes.controller;

import com.example.fun_formes.model.Form;
import com.example.fun_formes.service.FormeService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formes")
public class FormeController {

    private final FormeService formeService;

    @Autowired
    public FormeController(FormeService formeService) {
        this.formeService = formeService;
    }

    //GET
    @GetMapping
    public ResponseEntity<List<Form>> getAllFormes() {
        List<Form> formes = formeService.getAllFormes();
        if (formes.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 not found
        }
        return ResponseEntity.ok(formes); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable long id) {
        Form form = formeService.getFormById(id);
        if (form != null) {
            return ResponseEntity.ok(form);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping
    public ResponseEntity<Form> ajouterForme(@RequestBody Form form) {
        Form createdForm = formeService.ajouterForme(form);
        return ResponseEntity.ok(createdForm);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable long id, @RequestBody Form form) {
        Form updatedForm = formeService.updateForm(id, form);
        if (updatedForm != null) {
            return ResponseEntity.ok(updatedForm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerForme(@PathVariable long id) {
        try {
            formeService.supprimerForme(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}   
