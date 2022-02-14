package com.endiluamba.bookservice.controller;

import com.endiluamba.bookservice.model.Book;
import com.endiluamba.bookservice.proxy.ExchangeProxy;
import com.endiluamba.bookservice.repository.BookRepository;
import com.endiluamba.bookservice.response.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private ExchangeProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        var book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not found");

        var exchange = proxy.getExchange(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port + " FEIGN");
        book.setPrice(exchange.getConvertedValue());
        return book;
    }

    //Consuming Exchange microsservice via RestTemplate
    /*@GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        var book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity("http://localhost:8000/exchange-service/{amount}/{from}/{to}",
                        Exchange.class,
                        params);

        var exchange = response.getBody();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setPrice(exchange.getConvertedValue());
        return book;
    }*/
}
