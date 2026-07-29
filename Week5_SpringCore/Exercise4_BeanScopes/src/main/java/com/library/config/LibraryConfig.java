package com.library.config;

import com.library.aspect.LoggingAspect;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class LibraryConfig {

    // Singleton - only one instance (default)
    @Bean
    @Scope("singleton")
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    // Prototype - new instance every time
    @Bean
    @Scope("prototype")
    public BookService bookService() {
        BookService service = new BookService();
        service.setBookRepository(bookRepository());
        return service;
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}