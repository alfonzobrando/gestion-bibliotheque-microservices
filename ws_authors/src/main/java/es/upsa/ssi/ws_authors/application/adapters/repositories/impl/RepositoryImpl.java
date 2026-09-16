package es.upsa.ssi.ws_authors.application.adapters.repositories.impl;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.ws_authors.application.adapters.daos.DatabaseDao;
import es.upsa.ssi.ws_authors.application.adapters.repositories.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    DatabaseDao databaseDao;

    @Override
    public List<Author> findAuthors() throws BookshopException {
        return databaseDao.selectAuthors();
    }

    @Override
    public Optional<Author> findAuthor(String id) throws BookshopException {
        return databaseDao.selectAuthor(id);
    }

    @Override
    public Author createAuthor(Author author) throws BookshopException {
        return databaseDao.insertAuthor(author);
    }

    @Override
    public void updateAuthor(Author author) throws BookshopException {
        databaseDao.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(String id) throws BookshopException {
        databaseDao.deleteAuthor(id);
    }
}
