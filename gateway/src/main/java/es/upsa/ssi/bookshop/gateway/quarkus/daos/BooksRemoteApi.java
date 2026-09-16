package es.upsa.ssi.bookshop.gateway.quarkus.daos;

import es.upsa.ssi.bookshop.gateway.quarkus.daos.providers.BooksResponseExceptionMapper;
import es.upsa.ssi.common.dtos.UnidentifiedBook;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "books.remote.api")
@RegisterProvider(BooksResponseExceptionMapper.class)
@Path("books")
public interface BooksRemoteApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> queryBooks() throws BookshopException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book queryBookById(@PathParam("id") String id) throws BookshopException;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Book requestInsertBook(UnidentifiedBook unidentifiedBook) throws BookshopException;


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestUpdateBookById(@PathParam("id") String id, UnidentifiedBook unidentifiedBook) throws BookshopException;

    @DELETE
    @Path("{id}")
    public void requestRemoveBookById(@PathParam("id") String id) throws BookshopException;


    @OPTIONS
    public Response requestOptions();

}
