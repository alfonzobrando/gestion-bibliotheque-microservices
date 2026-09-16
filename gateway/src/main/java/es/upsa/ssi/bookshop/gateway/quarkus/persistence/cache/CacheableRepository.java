package es.upsa.ssi.bookshop.gateway.quarkus.persistence.cache;

import es.upsa.ssi.bookshop.gateway.quarkus.repository.Repository;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.redis.runtime.RedisCache;
import jakarta.annotation.PostConstruct;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@Cacheable
@Decorator
@Dependent
public class CacheableRepository implements Repository {

    @Inject
    @Any
    @Delegate
    Repository repository;

    @Inject
    @CacheName("authors")
    Cache authorsCache;

    @Inject
    @CacheName("books")
    Cache booksCache;

    @Inject
    Logger logger;

    RedisCache authorsRedisCache;
    RedisCache booksRedisCache;

    @PostConstruct
    public void init() {
        authorsRedisCache = authorsCache.as(RedisCache.class);
        booksRedisCache = booksCache.as(RedisCache.class);
    }

    @Override
    public List<Author> getAuthors() throws BookshopException {
        return repository.getAuthors();
    }

    @Override
    public Author getAuthorById(String id) throws BookshopException {
        Author author = authorsRedisCache.getOrNull(id, Author.class).await().indefinitely();
        if (author == null) {
            author = repository.getAuthorById(id);
            authorsRedisCache.put(id, author).await().indefinitely();
        } else {
            logger.log(Logger.Level.INFO, "Autor " + id + " encontrado en cache.");
        }
        return author;
    }

    @Override
    public Author addAuthor(Author author) throws BookshopException {
        Author newAuthor = repository.addAuthor(author);
        authorsRedisCache.put(newAuthor.id(), newAuthor).await().indefinitely();
        return newAuthor;
    }

    @Override
    public void replaceAuthor(Author author) throws BookshopException {
        repository.replaceAuthor(author);
    }

    @Override
    public void removeAuthor(String id) throws BookshopException {
        repository.removeAuthor(id);
        authorsRedisCache.invalidate(id).await().indefinitely();
    }




    @Override
    public List<Book> getBooks() throws BookshopException {
        return repository.getBooks();
    }

    @Override
    public Book getBookById(String id) throws BookshopException {
        Book book = booksRedisCache.getOrNull(id, Book.class).await().indefinitely();
        if (book == null) {
            book = repository.getBookById(id);
            booksRedisCache.put(id, book).await().indefinitely();
        } else {
            logger.log(Logger.Level.INFO, "Libro " + id + " encontrado en cache.");
        }
        return book;
    }

    @Override
    public Book addBook(Book book) throws BookshopException {
        Book newBook = repository.addBook(book);
        booksRedisCache.put(newBook.id(), newBook).await().indefinitely();
        return newBook;
    }

    @Override
    public void replaceBook(Book book) throws BookshopException {
        repository.replaceBook(book);
    }

    @Override
    public void removeBook(String id) throws BookshopException {
        repository.removeBook(id);
        booksRedisCache.invalidate(id).await().indefinitely();
    }
}
