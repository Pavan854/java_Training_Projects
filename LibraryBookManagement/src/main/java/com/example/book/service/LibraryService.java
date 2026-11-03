package com.example.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.book.exception.BookNotFoundException;
import com.example.book.model.Library;

@Service
public class LibraryService {

    private List<Library> librarylist = new ArrayList<>();

    private int nextId = 101;

   
    public List<Library> showBooks() {
        return librarylist;
    }

   
    public Library searchById(int id) {

        for (Library book : librarylist) {

            if (book.getBookId() == id) {
                return book;
            }
        }

        throw new BookNotFoundException("Book not found with ID: " + id);
    }

    
    public Library searchByName(String bookName) {

        for (Library book : librarylist) {

            if (book.getBookName().equalsIgnoreCase(bookName)) {
                return book;
            }
        }

        throw new BookNotFoundException("Book not found: " + bookName);
    }

   
    public Library addBook(Library book) {

        book.setBookId(nextId++);

        librarylist.add(book);

        return book;
    }

 
    public Library updateBook(int id, Library newBook) {

        Library oldBook = searchById(id);

        oldBook.setBookName(newBook.getBookName());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setAvailability(newBook.getAvailability());
        oldBook.setTotalCopies(newBook.getTotalCopies());

        return oldBook;
    }

    
    public String deleteBook(int id) {

        Library book = searchById(id);

        librarylist.remove(book);

        return "Book deleted successfully";
    }


    public Library patchBook(int id, Library newBook) {

        Library oldBook = searchById(id);

        if (newBook.getBookName() != null) {
            oldBook.setBookName(newBook.getBookName());
        }

        if (newBook.getAuthor() != null) {
            oldBook.setAuthor(newBook.getAuthor());
        }

        if (newBook.getPrice() != null) {
            oldBook.setPrice(newBook.getPrice());
        }

        if (newBook.getAvailability() != null) {
            oldBook.setAvailability(newBook.getAvailability());
        }

        if (newBook.getTotalCopies() != null) {
            oldBook.setTotalCopies(newBook.getTotalCopies());
        }

        return oldBook;
    }
}