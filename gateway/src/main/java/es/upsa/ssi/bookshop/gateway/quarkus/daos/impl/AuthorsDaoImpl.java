package es.upsa.ssi.bookshop.gateway.quarkus.daos.impl;

import es.upsa.ssi.bookshop.gateway.quarkus.daos.AuthorsDao;
import es.upsa.ssi.bookshop.gateway.quarkus.daos.AuthorsRemoteApi;
import es.upsa.ssi.common.dtos.UnidentifiedAuthor;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class AuthorsDaoImpl implements AuthorsDao {

    @Inject
    @RestClient
    AuthorsRemoteApi authorsRemoteApi;

    @Override
    public List<Author> selectAll() throws BookshopException {
        return authorsRemoteApi.queryAuthors();
    }

    @Override
    public Author selectById(String id) throws BookshopException {
        return authorsRemoteApi.queryAuthorById(id);
    }

    @Override
    public Author insert(Author author) throws BookshopException {
        UnidentifiedAuthor unidentifiedAuthor = UnidentifiedAuthor.builder()
                .withName(author.name())
                .withNationality(author.nationality())
                .build();

        return authorsRemoteApi.requestInsertAuthor(unidentifiedAuthor);
    }

    @Override
    public void update(Author author) throws BookshopException {
        UnidentifiedAuthor unidentifiedAuthor = UnidentifiedAuthor.builder()
                .withName(author.name())
                .withNationality(author.nationality())
                .build();
        authorsRemoteApi.requestUpdateAuthorById(author.id(), unidentifiedAuthor);
    }

    @Override
    public void delete(String id) throws BookshopException {
        authorsRemoteApi.requestRemoveAuthorById(id);
    }
}
