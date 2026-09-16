package es.upsa.ssi.ws_books.application.adapters.usecases;


import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface Service {
    public List<Book> getBooks() throws BookshopException;
    public Optional<Book> getBook(String id) throws BookshopException;
    public Book addBook(Book book) throws BookshopException;
    public void updateBook(Book book) throws BookshopException;
    public void deleteBook(String id) throws BookshopException;
}
