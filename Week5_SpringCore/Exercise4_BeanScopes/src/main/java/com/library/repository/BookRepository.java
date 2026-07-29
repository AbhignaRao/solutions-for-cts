package com.library.repository;

import com.library.model.Book;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Spring in Action", "Craig Walls"));
        books.add(new Book(2, "Clean Code", "Robert Martin"));
        books.add(new Book(3, "Design Patterns", "Gang of Four"));
        return books;
    }

    public Book findById(int id) {
        return findAll().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }
}