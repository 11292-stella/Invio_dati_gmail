package com.example.Invio_dati_gmail.controller;



import com.example.Invio_dati_gmail.dto.BlogPostDto;
import com.example.Invio_dati_gmail.exception.NonTrovatoException;
import com.example.Invio_dati_gmail.exception.ValidationException;
import com.example.Invio_dati_gmail.model.BlogPost;
import com.example.Invio_dati_gmail.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/blogpost")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlogPost(@RequestBody @Validated BlogPostDto blogPostDto, BindingResult bindingResult) throws NonTrovatoException, ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }

        return blogPostService.saveBlogPost(blogPostDto);
    }

    @GetMapping("/blogpost")
    public List<BlogPost> getBlogPosts(){

        return blogPostService.getBlogPosts();
    }

    @GetMapping("/blogpost/{id}")
    public BlogPost getBlogPost(@PathVariable int id) throws NonTrovatoException {
        return blogPostService.getBlogPost(id);
    }
    @PutMapping("/blogpost/{id}")
    public BlogPost updateBlogPost(@PathVariable int id,@RequestBody @Validated BlogPostDto blogPostDto,BindingResult bindingResult) throws NonTrovatoException, ValidationException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",(e,s)->e+s));
        }
        return blogPostService.updateBlogPost(id, blogPostDto);
    }

    @DeleteMapping("/blogpost/{id}")
    public void deleteBlogPost(@PathVariable int id) throws NonTrovatoException {
        blogPostService.deleteBlogPost(id);
    }
}
