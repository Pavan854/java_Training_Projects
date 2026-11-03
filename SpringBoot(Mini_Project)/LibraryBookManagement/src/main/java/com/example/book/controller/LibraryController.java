package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.book.model.Library;
import com.example.book.service.LibraryService;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService service;

   
    @GetMapping("/books")
    public List<Library> showBooks() {
        return service.showBooks();
    }

    
    @GetMapping("search/books/{id}")
    public Library searchById(@PathVariable int id) {
        return service.searchById(id);
    }

    
    @GetMapping("search/books/{name}")
    public Library searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

   
    @PostMapping("/insert")
    public Library addBook(@Valid @RequestBody Library book) {
        return service.addBook(book);
    }

   
    @PutMapping("update/books/{id}")
    public Library updateBook(
            @PathVariable int id,
            @Valid @RequestBody Library book) {

        return service.updateBook(id, book);
    }

    
    @DeleteMapping("delete/books/{id}")
    public String deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }

    @PatchMapping("partial/books/{id}")
    public Library patchBook(
            @PathVariable int id,
            @RequestBody Library book) {

        return service.patchBook(id, book);
    }
}