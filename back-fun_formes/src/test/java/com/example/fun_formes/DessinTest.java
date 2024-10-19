package com.example.fun_formes;
import com.example.fun_formes.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class DessinTest {

    private Dessin dessin;
    private Form cercle;
    private Form carre;
    private Form rectangle;
    private Form triangle;


    @BeforeEach
    public void initObjet() {
       //Initialise des objets avant chaque test pour eviter la repition du code 
        dessin = new Dessin(); 
        cercle = new Cercle(1,5,1,2,"Rouge");
        carre = new Carre(2,4,3,4,"Noir");  
        rectangle = new Rectangle(3,4, 5,6,7,"Jeune"); 
        triangle = new Triangle(4,4,4, 4,8,9,"Bleu"); 

    }

    @Test
    public void givenDessinVide_whenAjouterForme_thenFormeEstAjoutee() {
        // Given
        // dessin vide

        // When
        dessin.ajouterForme(cercle);
        // Then
        assertEquals(1, dessin.getFormes().size());
        assertEquals(cercle, dessin.getFormes().get(0));
        
    }

    @Test
    public void givenFormesAjoutees_whenCalculerPerimetreTotal_thenRetournePerimetreTotal() {
        // Given
        dessin.ajouterForme(cercle);
        dessin.ajouterForme(carre);
        // When
        double expectedPerimetre = cercle.calculerPerimetre() + carre.calculerPerimetre();
        // Then
        assertEquals(expectedPerimetre, dessin.calculerPerimetreTotal(), 0.001);
    }

    @Test
    public void givenFormesAjoutees_whenCalculerAireTotal_thenRetourneAireTotal() {
        // Given
        dessin.ajouterForme(cercle);
        dessin.ajouterForme(carre);

        // When
        double expectedAire = cercle.calculerAire() + carre.calculerAire();

        // Then
        assertEquals(expectedAire, dessin.calculerAireTotal(), 0.001);
    }

    @Test
    public void givenFormesAjoutees_whenCompterFigures_thenRetourneNombreCorrectDeChaqueForme() {
        // Given
        dessin.ajouterForme(cercle);
        dessin.ajouterForme(carre);
        dessin.ajouterForme(rectangle);
        dessin.ajouterForme(cercle);
        dessin.ajouterForme(triangle);

        // When
        Map<String, Integer> comptage = dessin.compterFigures();

        // Then
        Map<String, Integer> expectedComptage = new HashMap<>();
        expectedComptage.put("Cercle", 2);
        expectedComptage.put("Carre", 1);
        expectedComptage.put("Rectangle", 1);
        expectedComptage.put("Triangle", 1);

        assertEquals(expectedComptage, comptage); 
    }


}
