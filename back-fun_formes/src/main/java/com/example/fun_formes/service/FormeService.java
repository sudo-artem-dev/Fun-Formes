package com.example.fun_formes.service;

import com.example.fun_formes.model.Form;
import com.example.fun_formes.repository.FormeRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormeService {

    private final FormeRepository formeRepository;

    // Injection du repository via le constructeur
    // permet de spécifier que la classe a besoin d'une dépendance pour fonctionner
    @Autowired
    public FormeService(FormeRepository formeRepository) {
        this.formeRepository = formeRepository;
    }

    // Récupère toutes les formes depuis le repository 
    public List<Form> getAllFormes() {
        return formeRepository.findAll();
    }

    // Récupère une forme par ID
    public Form getFormById(long id) {
        return formeRepository.findById(id).orElse(null);
    }

    // Ajoute une forme à la base de données
    public Form ajouterForme(Form forme) {
        if (forme != null) {
            return formeRepository.save(forme);
        }
        return null;
    }
    

    // Met à jour une forme 
    public Form updateForm(long id, Form forme) {
        if (forme != null) {
            // Vérifie si l'entité existe 
            Form existingForm = formeRepository.findById(id).orElse(null);
            if (existingForm != null) {
                forme.setId(id); 
                return formeRepository.save(forme);
            } else {
                throw new EntityNotFoundException("Form not found with ID: " + id);
            }
        }
        return null;
    }
    
      
    // supprime la forme
    public void supprimerForme(long id) {
    if (formeRepository.existsById(id)) {
        formeRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Forme non trouvée avec l'ID : " + id);
    }
}


}
