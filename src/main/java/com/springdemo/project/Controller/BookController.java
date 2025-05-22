package com.springdemo.project.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping
    public String getAllBooks() {
        // This method can be accessed by authenticated users (since .anyRequest().authenticated() is set)
        return "List of all books (visible to any authenticated user)";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can add books
    public String addBook(@RequestBody String bookDetails) {
        return "Book added: " + bookDetails + " (Only ADMIN can do this)";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete books
    public String deleteBook(@PathVariable String id) {
        return "Book with ID " + id + " deleted (Only ADMIN can do this)";
    }

    @GetMapping("/my-books")
    @PreAuthorize("hasRole('USER')") // Only USER can see their own books (hypothetical)
    public String getMyBooks() {
        return "List of books for the current user (requires ROLE_USER)";
    }
}
