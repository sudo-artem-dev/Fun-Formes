package com.example.fun_formes.dto;

import java.util.List;

public class DessinParNiveauDTO {
    private String niveau;
    private List<DessinDTO> dessins;

    public DessinParNiveauDTO(String niveau, List<DessinDTO> dessins) {
        this.niveau = niveau;
        this.dessins = dessins;
    }

    // Getters et Setters
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<DessinDTO> getDessins() {
        return dessins;
    }

    public void setDessins(List<DessinDTO> dessins) {
        this.dessins = dessins;
    }
}
