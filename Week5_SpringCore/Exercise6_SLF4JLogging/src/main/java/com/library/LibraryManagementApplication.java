package com.library;

import com.library.config.LibraryConfig;
import com.library.model.Book;
import com.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class LibraryManagementApplication {

    private static final Logger logger = 
        LoggerFactory.getLogger(LibraryManagementApplication.class);

    public static void main(String[] args) {

        logger.info("Starting Library Management Application");

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(LibraryConfig.class);

        BookService bookService = context.getBean(BookService.class);

        logger.info("Fetching all books...");
        List<Book> books = bookService.getAllBooks();
        books.forEach(b -> logger.info("Book: {}", b));

        logger.info("Fetching book by ID 2...");
        Book book = bookService.getBookById(2);
        logger.info("Found: {}", book);

        logger.warn("Searching for non-existent book ID 99...");
        Book notFound = bookService.getBookById(99);
        logger.error("Result: {}", notFound);

        context.close();
        logger.info("Application stopped");
    }
}