package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class LibraryManagementApplication {
    public static void main(String[] args) {

        // load Spring context
        ApplicationContext context = 
            new ClassPathXmlApplicationContext("applicationContext.xml");

        // get bean
        BookService bookService = (BookService) context.getBean("bookService");

        // test
        System.out.println("-- All Books --");
        List<Book> books = bookService.getAllBooks();
        books.forEach(System.out::println);

        System.out.println("\n-- Find Book by ID --");
        Book book = bookService.getBookById(1);
        System.out.println(book);
    }
}