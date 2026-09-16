package es.upsa.ssi.bookshop.gateway.quarkus.repository.impl;

import es.upsa.ssi.bookshop.gateway.quarkus.daos.AuthorsDao;
import es.upsa.ssi.bookshop.gateway.quarkus.daos.BooksDao;
import es.upsa.ssi.bookshop.gateway.quarkus.repository.Repository;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    AuthorsDao authorsDao;

    @Inject
    BooksDao booksDao;

    @Inject
    Logger logger;

    @Override
    public List<Author> getAuthors() throws BookshopException {
        return authorsDao.selectAll();
    }

    @Override
    public Author getAuthorById(String id) throws BookshopException {
        return authorsDao.selectById(id);
    }

    @Override
    public Author addAuthor(Author author) throws BookshopException {
        return authorsDao.insert(author);
    }

    @Override
    public void replaceAuthor(Author author) throws BookshopException {
        authorsDao.update(author);
    }

    @Override
    public void removeAuthor(String id) throws BookshopException {
        authorsDao.delete(id);
    }




    @Override
    public List<Book> getBooks() throws BookshopException {
        return booksDao.selectAll();
    }

    @Override
    public Book getBookById(String id) throws BookshopException {
        return booksDao.selectById(id);
    }

    @Override
    public Book addBook(Book book) throws BookshopException {
        return booksDao.insert(book);
    }

    @Override
    public void replaceBook(Book book) throws BookshopException {
        booksDao.update(book);
    }

    @Override
    public void removeBook(String id) throws BookshopException {
        booksDao.delete(id);
    }
}
