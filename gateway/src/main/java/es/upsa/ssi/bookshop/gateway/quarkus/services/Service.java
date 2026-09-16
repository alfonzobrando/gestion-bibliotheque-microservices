package es.upsa.ssi.bookshop.gateway.quarkus.services;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;

public interface Service {

    public List<Author> requestFindAuthors() throws BookshopException;
    public Author requestAuthorById(String id) throws BookshopException;
    public void requestUpdateAuthor(Author author) throws BookshopException;
    public Author requestCreateAuthor(Author author) throws BookshopException;
    public void requestDeleteAuthor(String id) throws BookshopException;


    public List<Book> requestFindBooks() throws BookshopException;
    public Book requestBookById(String id) throws BookshopException;
    public void requestUpdateBook(Book book) throws BookshopException;
    public Book requestCreateBook(Book book) throws BookshopException;
    public void requestDeleteBook(String id) throws BookshopException;
}
