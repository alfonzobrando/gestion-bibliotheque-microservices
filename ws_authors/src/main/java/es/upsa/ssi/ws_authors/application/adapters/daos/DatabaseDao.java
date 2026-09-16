package es.upsa.ssi.ws_authors.application.adapters.daos;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;

import java.util.List;
import java.util.Optional;

public interface DatabaseDao {
    public List<Author> selectAuthors() throws BookshopException;
    public Optional<Author> selectAuthor(String id) throws BookshopException;
    public Author insertAuthor(Author author) throws BookshopException;
    public void updateAuthor(Author author) throws BookshopException;
    public void deleteAuthor(String id) throws BookshopException;
}
