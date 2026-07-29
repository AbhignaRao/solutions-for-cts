package com.library.config;

import com.library.aspect.LoggingAspect;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LibraryConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
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