package com.misbah.authorsapp.controller;


import com.misbah.authorsapp.model.Author;
import com.misbah.authorsapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public Author createAuthor(@RequestBody Author author)
    {
        return authorRepository.save(author);
    }
    @GetMapping
    public List<Author> getAllAuthors()
    {
        return authorRepository.findAll();
    }
    //READING AUTHORS BY ID
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id)
    {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id " + id));
    }
//    SEARCH FOR PARTIAL NAMES TOO
    @GetMapping("/search")
    public List<Author> searchAuthorsByName(@RequestParam String name)
    {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    //JPQL SEARCH
    @GetMapping("/search-jpql")
    public List<Author> searchAuthorsByNameJPQL(@RequestParam String keyword)
    {
        return authorRepository.searchByName(keyword);
    }

    // GETTING AUTHORS ABOVE CERTAIN AGE
    @GetMapping("/age-above/{age}")
    public List<Author> getAuthorsAboveAge(@PathVariable int age)
    {
        return authorRepository.findByAgeGreaterThan(age);
    }

    //COUNT OF ALL AUTHORS
    @GetMapping("/count")
    public Long countAuthors()
    {
        return authorRepository.count();
    }

    @GetMapping("/youngest")
    public Author getYoungestAuthor()
    {
        return authorRepository.findTopByOrderByAgeAsc();
    }

    @GetMapping("/oldest")
    public Author getOldestAuthor()
    {
        return authorRepository.findTopByOrderByAgeDesc();
    }

    //UPDATE AN AUTHOR BY ID
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id,@RequestBody Author updatedAuthor)
    {
        Author author=authorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found with id " + id));
        author.setName(updatedAuthor.getName());
        author.setAge(updatedAuthor.getAge());
        return authorRepository.save(author);
    }

    //DELETE BY ID
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id)
    {
        authorRepository.deleteById(id);
    }

}

