package es.upsa.ssi.ws_authors.application.adapters.usecases.impl;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.ws_authors.application.adapters.repositories.Repository;
import es.upsa.ssi.ws_authors.application.adapters.usecases.Service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceImpl implements Service {

    @Inject
    Repository repository;

    @Override
    public List<Author> getAuthors() throws BookshopException {
        return repository.findAuthors();
    }

    @Override
    public Optional<Author> getAuthor(String id) throws BookshopException {
        return repository.findAuthor(id);
    }

    @Override
    public Author addAuthor(Author author) throws BookshopException {
        return repository.createAuthor(author);
    }

    @Override
    public void updateAuthor(Author author) throws BookshopException {
        repository.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(String id) throws BookshopException {
        repository.deleteAuthor(id);
    }
}
