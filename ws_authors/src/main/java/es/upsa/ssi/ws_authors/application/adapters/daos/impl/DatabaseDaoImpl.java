package es.upsa.ssi.ws_authors.application.adapters.daos.impl;

import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.AuthorNotFoundException;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.ws_authors.application.adapters.daos.DatabaseDao;
import es.upsa.ssi.common.qualifiers.CustomDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// ---------- Configuracion MicroProfile Config ----------
//@DataSourceDefinition(name = "jdbc/authors",
//        className = "${MPCONFIG=database.driver}",
//        url = "${MPCONFIG=database.url}",
//        user = "${MPCONFIG=database.user}",
//        password = "${MPCONFIG=database.password}"
//)
@ApplicationScoped
public class DatabaseDaoImpl implements DatabaseDao {

//    @Resource(name = "jdbc/authors")
    @Inject
    @CustomDataSource
    DataSource dataSource;

    @Override
    public List<Author> selectAuthors() throws BookshopException {
        List<Author> authors = new ArrayList<>();

        final String SQL = """
                SELECT a.id, a.name, a.nationality
                FROM authors a
                """;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {

            while (resultSet.next()) {
                authors.add(
                        Author.builder()
                                .withId(resultSet.getString(1))
                                .withName(resultSet.getString(2))
                                .withNationality(resultSet.getString(3))
                                .build()
                );
            }

        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }

        return authors;
    }

    @Override
    public Optional<Author> selectAuthor(String id) throws BookshopException {
        final String SQL = """
                SELECT a.id, a.name, a.nationality
                FROM authors a
                WHERE a.id = ?
                """;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new AuthorNotFoundException(id);
                }
                return Optional.of(
                        Author.builder()
                                .withId(resultSet.getString(1))
                                .withName(resultSet.getString(2))
                                .withNationality(resultSet.getString(3))
                                .build()
                );
            }

        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public Author insertAuthor(Author author) throws BookshopException {
        final String SQL = """
                INSERT INTO authors
                (id, name, nationality)
                VALUES (nextval('seq_authors'), ?, ?)
                """;

        String[] column = {"id"};

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL, column)) {
            preparedStatement.setString(1, author.name());
            preparedStatement.setString(2, author.nationality());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                String id = resultSet.getString(1);
                return author.withId(id);
            }

        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public void updateAuthor(Author author) throws BookshopException {
        final String SQL = """
                UPDATE authors SET name = ?, nationality = ?
                WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, author.name());
            preparedStatement.setString(2, author.nationality());
            preparedStatement.setString(3, author.id());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new AuthorNotFoundException(author.id());
            }
        } catch (SQLException sqlException ) {
            throw new BookshopException(sqlException);
        }
    }

    @Override
    public void deleteAuthor(String id) throws BookshopException {
        final String SQL = """
                DELETE FROM authors WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new AuthorNotFoundException(id);
            }
        } catch (SQLException sqlException) {
            throw new BookshopException(sqlException);
        }
    }
}
