package com.example.Invio_dati_gmail.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private int id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private String urlImmagine;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;
}
