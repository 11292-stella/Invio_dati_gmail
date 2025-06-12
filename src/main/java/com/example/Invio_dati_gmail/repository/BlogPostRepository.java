package com.example.Invio_dati_gmail.repository;


import com.example.Invio_dati_gmail.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
