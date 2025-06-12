package com.example.Invio_dati_gmail.service;



import com.example.Invio_dati_gmail.dto.BlogPostDto;
import com.example.Invio_dati_gmail.exception.NonTrovatoException;
import com.example.Invio_dati_gmail.model.Autore;
import com.example.Invio_dati_gmail.model.BlogPost;
import com.example.Invio_dati_gmail.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreService autoreService;

    public BlogPost saveBlogPost(BlogPostDto blogPostDto) throws NonTrovatoException {
        Autore autore = autoreService.getAutore(blogPostDto.getAutoreId());

        BlogPost blogPost = new BlogPost();
        blogPost.setCover("https://picsum.photos/200/300");
        blogPost.setCategoria(blogPostDto.getCategoria());
        blogPost.setContenuto(blogPostDto.getContenuto());
        blogPost.setTitolo(blogPostDto.getTitolo());
        blogPost.setTempoDiLettura(blogPostDto.getTempoDiLettura());
        blogPost.setAutore(autore);

        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> getBlogPosts(){

        return blogPostRepository.findAll();
    }

    public BlogPost getBlogPost(int id) throws NonTrovatoException {
        return blogPostRepository.findById(id).
                orElseThrow(() -> new NonTrovatoException("BlogPost con id:" + id + " non trovato"));
    }

    public BlogPost updateBlogPost(int id, BlogPostDto blogPostDto) throws NonTrovatoException {
        BlogPost blogPostDaAggiornare = getBlogPost(id);

        blogPostDaAggiornare.setCategoria(blogPostDto.getCategoria());
        blogPostDaAggiornare.setContenuto(blogPostDto.getContenuto());
        blogPostDaAggiornare.setTitolo(blogPostDto.getTitolo());
        blogPostDaAggiornare.setTempoDiLettura(blogPostDto.getTempoDiLettura());

        if(blogPostDto.getAutoreId()!=blogPostDaAggiornare.getAutore().getId()){
            Autore autore = autoreService.getAutore(blogPostDto.getAutoreId());

            blogPostDaAggiornare.setAutore(autore);
        }

        return blogPostRepository.save(blogPostDaAggiornare);
    }


    public void deleteBlogPost(int id) throws NonTrovatoException {
        BlogPost blogPostDaRimuovere = getBlogPost(id);

        blogPostRepository.delete(blogPostDaRimuovere);
    }
}
