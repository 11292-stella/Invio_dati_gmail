package com.example.Invio_dati_gmail.service;



import com.cloudinary.Cloudinary;
import com.example.Invio_dati_gmail.dto.AutoreDto;
import com.example.Invio_dati_gmail.exception.NonTrovatoException;
import com.example.Invio_dati_gmail.model.Autore;
import com.example.Invio_dati_gmail.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Autore saveAutore(AutoreDto autoreDto){

        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        //autore.setAvatar("https://ui-avatars.com/api/?name="+autore.getNome()+"+"+autore.getCognome());

        sendMail(autore.getEmail());
        return autoreRepository.save(autore);

    }

    public Page<Autore> getAutori(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return autoreRepository.findAll(pageable);
    }

    public Autore getAutore(int id) throws NonTrovatoException {
        return autoreRepository.findById(id).
                orElseThrow(() -> new NonTrovatoException("Autore con id:" + id + " non trovato"));
    }

    public Autore updateAutore(int id, AutoreDto autoreDto) throws NonTrovatoException {
        Autore autoreDaAggiornare = getAutore(id);

        autoreDaAggiornare.setNome(autoreDto.getNome());
        autoreDaAggiornare.setCognome(autoreDto.getCognome());
        autoreDaAggiornare.setEmail(autoreDto.getEmail());
        autoreDaAggiornare.setDataDiNascita(autoreDto.getDataDiNascita());
        //autoreDaAggiornare.setAvatar("https://ui-avatars.com/api/?name="+autoreDto.getNome()+"+"+autoreDto.getCognome());

        return autoreRepository.save(autoreDaAggiornare);
    }


    public void deleteAutore(int id) throws NonTrovatoException {
        Autore autoreDaRimuovere = getAutore(id);

        autoreRepository.delete(autoreDaRimuovere);
    }

    public Autore patchAutore(int id, MultipartFile file) throws NonTrovatoException, IOException, IOException {
        Autore autore = getAutore(id);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap());
        String url = (String) uploadResult.get("url"); // ottieni l'URL dell'immagine

        autore.setAvatar(url); // imposta l'avatar con l'URL
        autoreRepository.save(autore);

        return autore;
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}
