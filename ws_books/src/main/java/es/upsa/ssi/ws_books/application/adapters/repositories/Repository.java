package es.upsa.ssi.ws_books.application.adapters.repositories;

import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface Repository {
    public List<Book> findBooks() throws BookshopException;
    public Optional<Book> findABook(String id) throws BookshopException;
    public Book createBook(Book book) throws BookshopException;
    public void updateBook(Book book) throws BookshopException;
    public void deleteBook(String id) throws BookshopException;
}
