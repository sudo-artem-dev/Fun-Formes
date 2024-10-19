package com.example.fun_formes.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;

@Entity 
@JsonTypeName("carre")
public class Carre extends Form {
    
    private double cote;

    //Constructeur par défaut pour JPA
    public Carre() {
        super();
    }

    //Constructeur avec paramètres
    public Carre(long id, double cote, double x, double y, String couleur) {
        super(id, x, y, couleur);
        this.cote = cote;
    }
    
     //Getters
    public double getCote() {
        return cote;
    }
    //Setters
    public void setCote(double cote) {
        this.cote = cote;
    }

    
    //Méthodes
    @Override
    public double calculerPerimetre() {
        return 4 * getCote();
    }

    @Override
    public double calculerAire() {
        return getCote() * getCote();
    }

    @Override
    public String toString(){
    return "Voici mon carré où chaque côté mesure " + getCote() + " cm de couleur " + getCouleur() + ", périmètre = " + calculerPerimetre() + " cm, aire = " + calculerAire() + " cm²";

    }

}
