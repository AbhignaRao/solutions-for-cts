package com.library;

import com.library.config.LibraryConfig;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class LibraryManagementApplication {
    public static void main(String[] args) {

        // Java-based config - no XML needed!
        ApplicationContext context =
            new AnnotationConfigApplicationContext(LibraryConfig.class);

        BookService bookService = context.getBean(BookService.class);

        System.out.println("-- All Books (Java Config) --");
        List<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);

        System.out.println("\n-- Find Book by ID --");
        Book book = bookService.getBookById(2);
        System.out.println(book);
    }
}