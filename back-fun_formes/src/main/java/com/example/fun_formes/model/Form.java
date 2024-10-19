package com.example.fun_formes.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //chaque sous-type est stocké dans sa propre table et les tables sont jointes en fonction de la clé primaire
@JsonTypeInfo( //inclut des informations sur le type d'objet
    use = JsonTypeInfo.Id.NAME, //Indique la méthode utilisée pour identifier le type (nom)
    include = JsonTypeInfo.As.PROPERTY, // Indique comment inclure les informations de type dans le JSON ("type")
    property = "type" //Spécifie le nom de la propriété qui contiendra le type d'objet dans le JSON (type)
)
@JsonSubTypes({ 
    // Déclare les sous-types pour la sérialisation et désérialisation JSON
    @JsonSubTypes.Type(value = Cercle.class, name = "Cercle"),
    @JsonSubTypes.Type(value = Carre.class, name = "Carre"),
    @JsonSubTypes.Type(value = Rectangle.class, name = "Rectangle"),
    @JsonSubTypes.Type(value = Triangle.class, name = "Triangle")
})
public abstract class Form {

    @Id // un attribut comme étant la clé primaire de l'entité
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génère la valeur de la clé primaire (l'ID) automatiquement avec auto-incrémentation

    private long id;
    private double x;
    private double y;
    private String couleur;

    //Constructeurs
    public Form() {}
    
    public Form (long id, double x, double y, String couleur){
        this.id = id;
        this.x = x;
        this.y= y;        
        this.couleur = couleur;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    // Méthodes abstraites
    public abstract double calculerPerimetre();
    public abstract double calculerAire();



    
}

