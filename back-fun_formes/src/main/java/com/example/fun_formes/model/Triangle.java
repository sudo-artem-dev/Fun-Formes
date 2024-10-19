package com.example.fun_formes.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("triangle")
public class Triangle extends Form{
    private double coteA;
    private double coteB;
    private double coteC;


    //Constructeurs
    public Triangle() {}

    public Triangle (long id, double coteA, double coteB, double coteC, double x, double y, String couleur){
        super(id, x, y, couleur);
        this.coteA = coteA;
        this.coteB = coteB;
        this.coteC = coteC;
    }

    //Getters
    public double getCoteA() {
        return coteA;
    }
    public double getCoteB() {
        return coteB;
    }
    public double getCoteC() {
        return coteC;
    }

    //Setters
    public void setCoteA(double coteA) {
        this.coteA = coteA;
    }
    public void setCoteB(double coteB) {
        this.coteB = coteB;
    }
    public void setCoteC(double coteC) {
        this.coteC = coteC;
    }


    //Méthodes
    @Override
    public double calculerPerimetre() {
        return getCoteA() + getCoteB() + getCoteC();
    }

    // Calcule de l'aire avec la formule de Héron
    @Override
    public double calculerAire() {
        double s = (coteA + coteB + coteC) / 2;
        return Math.sqrt(s * (s - coteA) * (s - coteB) * (s - coteC));
    }
     
    @Override
    public String toString() {
        return "Voici mon triangle avec des côtés de " + getCoteA() + " cm, " + getCoteB() + " cm et " + getCoteC() + " cm, de couleur " + getCouleur() + 
               ", périmètre = " + calculerPerimetre() + " cm, aire = " + calculerAire() + " cm²";
    }
    

}
