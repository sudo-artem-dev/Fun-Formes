package com.example.fun_formes.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@Entity
@JsonPropertyOrder({"niveau","id", "nom", "totalFormes", "nombreParType", "formes" })
public class Dessin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String niveau; 

    @OneToMany( // Définit une relation où une entité peut avoir plusieurs entités associées.
        cascade = CascadeType.ALL, // Applique toutes les opérations de persistance à la collection d'entités enfants
        fetch = FetchType.LAZY, // Charge les entités enfants uniquement à la demande, améliorant les performances
        orphanRemoval = true // Supprime automatiquement les entités enfants qui ne sont plus référencées par l'entité parent
    )
    @JoinColumn(name = "dessin_id") // liaison entre la table dessin et les formes
    private List<Form> formes;

    // Constructeur par défaut pour JPA
    public Dessin() {
        formes = new ArrayList<>();
    }
    @JsonGetter("totalFormes")
    public int getTotalFormes() {
        return formes.size();
    }
    public String getNiveau() {
        return niveau;
    }
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public List<Form> getFormes() {
        return formes;
    }
    public void ajouterForme(Form forme) {
        formes.add(forme);
    }
    @JsonGetter("perimetreTotal")
    public double calculerPerimetreTotal() {
        double perimetreTotal = 0;
        for (Form forme : formes) {
            perimetreTotal += forme.calculerPerimetre();
        }
        return perimetreTotal;
    }
    @JsonGetter("aireTotal")
    public double calculerAireTotal() {
        double aireTotal = 0;
        for (Form forme : formes) {
            aireTotal += forme.calculerAire();
        }
        return aireTotal;
    }

    @JsonGetter("nombreParType")
    public Map<String, Integer> compterFigures() {
        //stocke les types de formes comme clés (String) et le nombre de ces formes comme valeurs (Integer)
        Map<String, Integer> comptage = new HashMap<>();
        for (Form forme : formes) {
            //récupère le nom de la classe de l'objet
            String type = forme.getClass().getSimpleName();
            // getOrDefault(type, 0) renvoie la valeur actuelle associée à la clé "type" ou 0 si elle existe pas encore
            comptage.put(type, comptage.getOrDefault(type, 0) + 1);
        }
        return comptage;
    }

    @Override
    public String toString() {
        return "contient " + formes.size() + " formes " + compterFigures() + " avec une aire totale de " + calculerAireTotal() + " cm² et un périmètre total de " + calculerPerimetreTotal() + " cm.";
    }
}