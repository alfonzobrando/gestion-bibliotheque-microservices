package es.upsa.ssi.ws_authors.application.adapters.repositories;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface Repository {
    public List<Author> findAuthors() throws BookshopException;
    public Optional<Author> findAuthor(String id) throws BookshopException;
    public Author createAuthor(Author author) throws BookshopException;
    public void updateAuthor(Author author) throws BookshopException;
    public void deleteAuthor(String id) throws BookshopException;
}
