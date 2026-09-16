package es.upsa.ssi.bookshop.gateway.quarkus.daos.providers;

import es.upsa.ssi.common.exceptions.AuthorNotFoundException;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class AuthorsResponseExceptionMapper implements ResponseExceptionMapper<BookshopException> {

    @Override
    public BookshopException toThrowable(Response response) {
//        return new AuthorNotFoundException(response.readEntity(String.class).split(":")[1]);
        return switch (response.getStatusInfo().getFamily()) {
            case CLIENT_ERROR -> new AuthorNotFoundException();
            default -> new BookshopException("Error desconocido");
        };
    }
}
