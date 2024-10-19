package com.example.fun_formes;

import com.example.fun_formes.model.*;


public class Main {
    public static void main(String[] args) {
    //Cercles
    Form cercle = new Cercle(1,10, 2,3,"Rouge");
    System.out.println(cercle); 
    Form cercle2 = new Cercle(2,5, 4,5,"Vert");
    System.out.println(cercle2); 
    //Rectangles
    Form rectangle = new Rectangle(3,5,10,6,7,"Rouge");
    System.out.println(rectangle); 
    Form rectangle2 = new Rectangle(4,10, 15, 8,9,"Bleu");
    System.out.println(rectangle2); 
    //Triangles
    Form triangleEquilateral = new Triangle(5,3,3,3,10,11,"Jeune");
    System.out.println(triangleEquilateral); 
    Form triangleRectangle = new Triangle(6,3, 4, 5, 12,13,"Vert");
    System.out.println(triangleRectangle); 
    //Carrés
    Form carre = new Carre(7,6,14,15,"Noir");
    System.out.println(carre);

    // création d'un dessin représentant une fusée avec des formes géométriques (composition)
    Dessin fusee = new Dessin();
    fusee.ajouterForme(cercle);
    fusee.ajouterForme(rectangle);
    fusee.ajouterForme(carre);
    fusee.ajouterForme(triangleEquilateral);
    fusee.ajouterForme(triangleRectangle);
    System.out.println("Le Dessin de la Fusée " + fusee); 

    // création d'un dessin représentant une fusée avec des formes géométriques (composition)
    Dessin voiture = new Dessin();
    voiture.ajouterForme(cercle);
    voiture.ajouterForme(cercle2);
    voiture.ajouterForme(triangleEquilateral);
    voiture.ajouterForme(carre);
    System.out.println("Le Dessin de la Voiture " + voiture); 
    }
}