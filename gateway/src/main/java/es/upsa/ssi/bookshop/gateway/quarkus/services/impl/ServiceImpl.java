package es.upsa.ssi.bookshop.gateway.quarkus.services.impl;

import es.upsa.ssi.bookshop.gateway.quarkus.repository.Repository;
import es.upsa.ssi.bookshop.gateway.quarkus.services.Service;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Author> requestFindAuthors() throws BookshopException {
        return repository.getAuthors();
    }

    @Override
    public Author requestAuthorById(String id) throws BookshopException {
        return repository.getAuthorById(id);
    }

    @Override
    public void requestUpdateAuthor(Author author) throws BookshopException {
        repository.replaceAuthor(author);
    }

    @Override
    public Author requestCreateAuthor(Author author) throws BookshopException {
        return repository.addAuthor(author);
    }

    @Override
    public void requestDeleteAuthor(String id) throws BookshopException {
        repository.removeAuthor(id);
    }

    @Override
    public List<Book> requestFindBooks() throws BookshopException {
        return repository.getBooks();
    }

    @Override
    public Book requestBookById(String id) throws BookshopException {
        return repository.getBookById(id);
    }

    @Override
    public void requestUpdateBook(Book book) throws BookshopException {
        repository.replaceBook(book);
    }

    @Override
    public Book requestCreateBook(Book book) throws BookshopException {
        return repository.addBook(book);
    }

    @Override
    public void requestDeleteBook(String id) throws BookshopException {
        repository.removeBook(id);
    }
}
