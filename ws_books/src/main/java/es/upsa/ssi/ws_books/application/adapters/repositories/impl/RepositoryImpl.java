package es.upsa.ssi.ws_books.application.adapters.repositories.impl;

import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.ws_books.application.adapters.daos.DatabaseDao;
import es.upsa.ssi.ws_books.application.adapters.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    DatabaseDao databaseDao;

    @Override
    public List<Book> findBooks() throws BookshopException {
        return databaseDao.selectBooks();
    }

    @Override
    public Optional<Book> findABook(String id) throws BookshopException {
        return databaseDao.selectBook(id);
    }

    @Override
    public Book createBook(Book book) throws BookshopException {
        return databaseDao.insertBook(book);
    }

    @Override
    public void updateBook(Book book) throws BookshopException {
        databaseDao.updateBook(book);
    }

    @Override
    public void deleteBook(String id) throws BookshopException {
        databaseDao.deleteBook(id);
    }
}
