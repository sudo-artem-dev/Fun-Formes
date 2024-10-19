package com.example.fun_formes.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("cercle")
public class Cercle extends Form{
    private double rayon;


    //Constructeurs
    public Cercle() {
        super();
    }
    public Cercle(long id, double rayon, double x, double y, String couleur) {
        super(id, x, y, couleur);
        this.rayon = rayon;
    }

    //Getters
    public double getRayon() {
        return rayon;
    }

    //Setters
    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    //Méthode
    @Override
    public double calculerPerimetre() {
        return 2 * Math.PI * getRayon();
    }

    @Override
    public double calculerAire() {
        return Math.PI * getRayon() * getRayon();
    }

    @Override
    public String toString() {
        return "Voici mon cercle avec un rayon de " + getRayon() + " cm de couleur " + getCouleur() + ", périmètre = " + calculerPerimetre() + " cm, aire = " + calculerAire() + " cm²";
        
    }
    
}
