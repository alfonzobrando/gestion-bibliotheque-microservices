package es.upsa.ssi.ws_books.application.adapters.daos.impl;

import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookNotFoundException;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.common.qualifiers.CustomDataSource;
import es.upsa.ssi.ws_books.application.adapters.daos.DatabaseDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class DatabaseDaoImpl implements DatabaseDao {

    @Inject
    @CustomDataSource
    DataSource dataSource;

    @Override
    public List<Book> selectBooks() throws BookshopException {
        List<Book> books = new ArrayList<>();

        final String SQL = """
                SELECT b.id, b.title, b.year, b.author_id
                FROM books b
                """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {

            while (resultSet.next()) {
                books.add(
                        Book.builder()
                                .withId(resultSet.getString(1))
                                .withTitle(resultSet.getString(2))
                                .withYear(resultSet.getString(3))
                                .withAuthor_id(resultSet.getString(4))
                                .build()
                );
            }
        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }

        return books;
    }

    @Override
    public Optional<Book> selectBook(String id) throws BookshopException {
        final String SQL = """
                SELECT b.id, b.title, b.year, b.author_id
                FROM books b
                WHERE b.id = ?
                """;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new BookNotFoundException(id);
                }
                return Optional.of(
                        Book.builder()
                                .withId(resultSet.getString(1))
                                .withTitle(resultSet.getString(2))
                                .withYear(resultSet.getString(3))
                                .withAuthor_id(resultSet.getString(4))
                                .build()
                );
            }
        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public Book insertBook(Book book) throws BookshopException {
        final String SQL = """
                INSERT INTO books (id, title, year, author_id) VALUES (nextval('seq_books'), ?, ?, ?)
                """;
        String[] column = {"id"};

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL, column)) {
            preparedStatement.setString(1, book.title());
            preparedStatement.setString(2, book.year());
            preparedStatement.setString(3, book.author_id());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                String id = resultSet.getString(1);
                return book.withId(id);
            }

        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public void updateBook(Book book) throws BookshopException {
        final String SQL = """
                UPDATE books SET title = ?, year = ?, author_id = ? WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, book.title());
            preparedStatement.setString(2, book.year());
            preparedStatement.setString(3, book.author_id());
            preparedStatement.setString(4, book.id());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new BookNotFoundException(book.id());
            }
        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public void deleteBook(String id) throws BookshopException {
        final String SQL = """
                DELETE FROM books WHERE id = ?
                """;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new BookNotFoundException(id);
            }
        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }
}
