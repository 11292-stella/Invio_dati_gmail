package com.example.Invio_dati_gmail.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDto {

    @NotEmpty(message = "il nome non puo essere nullo")
    private String nome;
    @NotEmpty(message = "il cognome non puo essere nullo")
    private String cognome;
    @NotEmpty(message = "il campo email non puo essere nullo")
    @Email(message = "Formato email non corretto")
    private String email;
    @NotNull(message = "data di nascita non puo essere nullo")
    private LocalDate dataDiNascita;
}
