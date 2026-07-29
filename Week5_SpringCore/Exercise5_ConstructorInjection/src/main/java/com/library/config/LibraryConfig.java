package com.library.config;

import com.library.aspect.LoggingAspect;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class LibraryConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public BookService bookService() {
        // constructor injection
        return new BookService(bookRepository());
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}