package es.upsa.ssi.bookshop.gateway.quarkus.repository;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;

public interface Repository {

    public List<Author> getAuthors() throws BookshopException;
    public Author getAuthorById(String id) throws BookshopException;
    public Author addAuthor(Author author) throws BookshopException;
    public void replaceAuthor(Author author) throws BookshopException;
    public void removeAuthor(String id) throws BookshopException;

    public List<Book> getBooks() throws BookshopException;
    public Book getBookById(String id) throws BookshopException;
    public Book addBook(Book book) throws BookshopException;
    public void replaceBook(Book book) throws BookshopException;
    public void removeBook(String id) throws BookshopException;
}
