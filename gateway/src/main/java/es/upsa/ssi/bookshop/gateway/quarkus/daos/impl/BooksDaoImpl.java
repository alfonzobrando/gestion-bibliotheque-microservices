package es.upsa.ssi.bookshop.gateway.quarkus.daos.impl;

import es.upsa.ssi.bookshop.gateway.quarkus.daos.BooksDao;
import es.upsa.ssi.bookshop.gateway.quarkus.daos.BooksRemoteApi;
import es.upsa.ssi.common.dtos.UnidentifiedBook;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class BooksDaoImpl implements BooksDao {

    @Inject
    @RestClient
    BooksRemoteApi booksRemoteApi;


    @Override
    public List<Book> selectAll() throws BookshopException {
        return booksRemoteApi.queryBooks();
    }

    @Override
    public Book selectById(String id) throws BookshopException {
        return booksRemoteApi.queryBookById(id);
    }

    @Override
    public Book insert(Book book) throws BookshopException {
        UnidentifiedBook unidentifiedBook = UnidentifiedBook.builder()
                .withTitle(book.title())
                .withYear(book.year())
                .withAuthor_id(book.author_id())
                .build();

        return booksRemoteApi.requestInsertBook(unidentifiedBook);
    }

    @Override
    public void update(Book book) throws BookshopException {
        UnidentifiedBook unidentifiedBook = UnidentifiedBook.builder()
                .withTitle(book.title())
                .withYear(book.year())
                .withAuthor_id(book.author_id())
                .build();

        booksRemoteApi.requestUpdateBookById(book.id(), unidentifiedBook);
    }

    @Override
    public void delete(String id) throws BookshopException {
        booksRemoteApi.requestRemoveBookById(id);
    }
}
