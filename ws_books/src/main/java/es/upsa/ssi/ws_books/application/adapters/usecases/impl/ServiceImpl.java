package es.upsa.ssi.ws_books.application.adapters.usecases.impl;

import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.ws_books.application.adapters.repositories.Repository;
import es.upsa.ssi.ws_books.application.adapters.usecases.Service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Book> getBooks() throws BookshopException {
        return repository.findBooks();
    }

    @Override
    public Optional<Book> getBook(String id) throws BookshopException {
        return repository.findABook(id);
    }

    @Override
    public Book addBook(Book book) throws BookshopException {
        return repository.createBook(book);
    }

    @Override
    public void updateBook(Book book) throws BookshopException {
        repository.updateBook(book);
    }

    @Override
    public void deleteBook(String id) throws BookshopException {
        repository.deleteBook(id);
    }
}
