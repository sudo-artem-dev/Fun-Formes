package com.example.fun_formes.service;

import com.example.fun_formes.model.Dessin;
import com.example.fun_formes.model.Form;
import com.example.fun_formes.repository.DessinRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessinService {

    private final DessinRepository dessinRepository;

    @Autowired
    public DessinService(DessinRepository dessinRepository) {
        this.dessinRepository = dessinRepository;
    }

    // méthode pour récupérer la liste des dessins
    public List<Dessin> getAllDessins() {
        return dessinRepository.findAll();
    }
    // Récupérer les dessins par niveau
    public List<Dessin> getDessinsByNiveau(String niveau) {
        return dessinRepository.findByNiveau(niveau);
    }
    // méthode pour créer un dessin
    public Dessin createDessin(Dessin dessin) {
        return dessinRepository.save(dessin);
    }
    // méthode pour récupérer un dessin par id
    public Dessin getDessinById(long id) {
    return dessinRepository.findById(id).orElse(null);
    }
    // méthode pour créer une forme dans le dessin
    public void addFormeToDessin(Long dessinId, Form forme) {
    Dessin dessin = dessinRepository.findById(dessinId).orElse(null);
    if (dessin != null) {
        dessin.getFormes().add(forme); 
        dessinRepository.save(dessin); 
    } else {
        throw new EntityNotFoundException("Dessin not found with ID: " + dessinId);
    }
}

    // méthode pour récupérer toutes les formes d'un dessin spécifique
    public List<Form> getFormesFromDessin(Long dessinId) {
        return dessinRepository.findById(dessinId)
                               .map(Dessin::getFormes)
                               .orElse(null);
    }
    public Dessin getDessinByNiveauAndId(String niveau, Long id) {
        // Récupérer le dessin par niveau et par ID
        return dessinRepository.findByNiveauAndId(niveau, id);
    }
    // méthode pour mettre à jour le dessin
    public Dessin updateDessin(long id, Dessin dessin) {
        if (dessin != null) {

            Dessin existingDessin = dessinRepository.findById(id).orElse(null);
            if (existingDessin != null) {

                // s'assurer qu'il correspond à l'ID de l'entité existante 
                dessin.setId(id); 

                return dessinRepository.save(dessin);
            } else {

                throw new EntityNotFoundException("Dessin not found with ID: " + id);
            }
        }
        return null;
    }

    // méthode pour supprimer le dessin
    public void supprimerDessin(long id) {
    if (dessinRepository.existsById(id)) {
        dessinRepository.deleteById(id);
    } else {
        throw new EntityNotFoundException("Dessin non trouvé avec l'ID : " + id);
    }
}
}