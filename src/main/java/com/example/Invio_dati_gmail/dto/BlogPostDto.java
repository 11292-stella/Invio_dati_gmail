package com.example.Invio_dati_gmail.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogPostDto {
    @NotEmpty(message = "il nome della categoria non puo essere nullo")
    private String categoria;
    @NotEmpty(message = "il nome del titolo non puo essere nullo")
    private String titolo;
    @NotEmpty(message = "il contenuto non puo essere nullo")
    private String contenuto;
    @NotNull(message = "il tempo non puo essere null")
    private int tempoDiLettura;
    private int autoreId;
}
