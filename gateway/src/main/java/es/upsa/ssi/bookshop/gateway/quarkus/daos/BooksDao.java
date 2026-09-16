package es.upsa.ssi.bookshop.gateway.quarkus.daos;

import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public interface BooksDao {
    public List<Book> selectAll() throws BookshopException;

    public Book selectById(String id) throws BookshopException;

    public Book insert(Book book) throws BookshopException;

    public void update(Book book) throws BookshopException;

    public void delete(String id) throws BookshopException;
}
