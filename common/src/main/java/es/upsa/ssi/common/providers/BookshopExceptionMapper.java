package es.upsa.ssi.common.providers;

import es.upsa.ssi.common.exceptions.AuthorNotFoundException;
import es.upsa.ssi.common.exceptions.BookNotFoundException;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookshopExceptionMapper implements ExceptionMapper<BookshopException> {

    @Override
    public Response toResponse(BookshopException exception) {

        if (exception instanceof AuthorNotFoundException || exception instanceof BookNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
        }


        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }


}
