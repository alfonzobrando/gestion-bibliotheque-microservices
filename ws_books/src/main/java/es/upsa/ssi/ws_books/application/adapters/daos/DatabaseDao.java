package es.upsa.ssi.ws_books.application.adapters.daos;


import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface DatabaseDao {
    public List<Book> selectBooks() throws BookshopException;
    public Optional<Book> selectBook(String id) throws BookshopException;
    public Book insertBook(Book book) throws BookshopException;
    public void updateBook(Book book) throws BookshopException;
    public void deleteBook(String id) throws BookshopException;
}
