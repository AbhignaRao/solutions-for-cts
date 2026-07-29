package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        logger.info("BookService initialized with constructor injection");
    }

    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        List<Book> books = bookRepository.findAll();
        logger.debug("Found {} books", books.size());
        return books;
    }

    public Book getBookById(int id) {
        logger.info("Fetching book with ID: {}", id);
        Book book = bookRepository.findById(id);
        if (book != null) {
            logger.debug("Found book: {}", book.getTitle());
        } else {
            logger.warn("Book not found with ID: {}", id);
        }
        return book;
    }
}