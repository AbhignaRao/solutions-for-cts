package com.library;

import com.library.config.LibraryConfig;
import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class LibraryManagementApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(LibraryConfig.class);

        BookService bookService = context.getBean(BookService.class);

        System.out.println("\n-- All Books (Constructor Injection) --");
        List<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);

        System.out.println("\n-- Find Book by ID --");
        Book book = bookService.getBookById(3);
        System.out.println(book);

        context.close();
    }
}