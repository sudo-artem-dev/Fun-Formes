package com.example.fun_formes.dto;
import com.example.fun_formes.model.Form; 

import java.util.List;

import java.util.Map; 

public class DessinDTO {
    private Long id;
    private String nom;
    private int totalFormes;
    private Map<String, Integer> nombreParType;
    private List<Form> formes;
    private double perimetreTotal;
    private double aireTotal;

    public DessinDTO(Long id, String nom, int totalFormes, Map<String, Integer> nombreParType,List<Form> formes, double perimetreTotal, double aireTotal) {
        this.id = id;
        this.nom = nom;
        this.totalFormes = totalFormes;
        this.nombreParType = nombreParType;
        this.formes = formes;
        this.perimetreTotal = perimetreTotal;
        this.aireTotal = aireTotal;
    }
    // Getters et Setters
    public Map<String, Integer> getNombreParType() {
        return nombreParType;
    }
    public void setNombreParType(Map<String, Integer> nombreParType) {
        this.nombreParType = nombreParType;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTotalFormes() {
        return totalFormes;
    }

    public void setTotalFormes(int totalFormes) {
        this.totalFormes = totalFormes;
    }

    public List<Form> getFormes() {
        return formes;
    }

    public void setFormes(List<Form> formes) {
        this.formes = formes;
    }

    public double getPerimetreTotal() {
        return perimetreTotal;
    }
    public void setPerimetreTotal(double perimetreTotal) {
        this.perimetreTotal = perimetreTotal;
    }

    public double getAireTotal() {
        return aireTotal;
    }
    public void setAireTotal(double aireTotal) {
        this.aireTotal = aireTotal;
    }
}
