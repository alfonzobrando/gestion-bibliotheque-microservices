package es.upsa.ssi.bookshop.gateway.quarkus.daos;


import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {
    public List<Author> selectAll() throws BookshopException;

    public Author selectById(String id) throws BookshopException;

    public Author insert(Author author) throws BookshopException;

    public void update(Author author) throws BookshopException;

    public void delete(String id) throws BookshopException;
}
