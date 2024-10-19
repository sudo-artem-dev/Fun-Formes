package com.example.fun_formes.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("rectangle")
public class Rectangle extends Form {
    private double longueur;
    private double largeur;

    //Constructeurs
    public Rectangle() {}

    public Rectangle(long id, double longueur, double largeur, double x, double y, String couleur) {
        super(id, x, y, couleur);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    //Getters
    public double getLongueur() {
        return longueur;
    }
    public double getLargeur() {
        return largeur;
    }

    //Setters
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    //Méthodes
    @Override
    public double calculerPerimetre() {
        return 2 * (getLongueur() + getLargeur());
    }

    @Override
    public double calculerAire() {
        return getLongueur() * getLargeur();
    }

    @Override
    public String toString(){
        return "Voici mon rectangle avec une largeur de " + getLargeur() + " cm, une longueur de " + getLongueur()  + " cm de couleur " + getCouleur() + ", périmètre = " + calculerPerimetre() + " cm, aire = " + calculerAire() + " cm²";
    }


}
