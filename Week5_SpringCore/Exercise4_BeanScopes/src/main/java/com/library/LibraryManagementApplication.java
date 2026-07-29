package com.library;

import com.library.config.LibraryConfig;
import com.library.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(LibraryConfig.class);

        // Singleton test - both should be SAME instance
        com.library.repository.BookRepository repo1 = 
            context.getBean(com.library.repository.BookRepository.class);
        com.library.repository.BookRepository repo2 = 
            context.getBean(com.library.repository.BookRepository.class);
        System.out.println("-- Singleton Scope --");
        System.out.println("Same instance? " + (repo1 == repo2));

        // Prototype test - both should be DIFFERENT instances
        BookService service1 = context.getBean(BookService.class);
        BookService service2 = context.getBean(BookService.class);
        System.out.println("\n-- Prototype Scope --");
        System.out.println("Same instance? " + (service1 == service2));

        context.close();
    }
}