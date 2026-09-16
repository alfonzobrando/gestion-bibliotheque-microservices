package es.upsa.ssi.ws_authors.application.adapters.usecases;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface Service {

    public List<Author> getAuthors() throws BookshopException;
    public Optional<Author> getAuthor(String id) throws BookshopException;
    public Author addAuthor(Author author) throws BookshopException;
    public void updateAuthor(Author author) throws BookshopException;
    public void deleteAuthor(String id) throws BookshopException;
}
