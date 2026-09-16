package es.upsa.ssi.bookshop.gateway.quarkus.daos;

import es.upsa.ssi.bookshop.gateway.quarkus.daos.providers.AuthorsResponseExceptionMapper;
import es.upsa.ssi.common.dtos.UnidentifiedAuthor;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "authors.remote.api")
@RegisterProvider(AuthorsResponseExceptionMapper.class)
@Path("authors")
public interface AuthorsRemoteApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> queryAuthors() throws BookshopException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Author queryAuthorById(@PathParam("id") String id) throws BookshopException;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Author requestInsertAuthor(UnidentifiedAuthor unidentifiedAuthor) throws BookshopException;


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void requestUpdateAuthorById(@PathParam("id") String id, UnidentifiedAuthor unidentifiedAuthor) throws BookshopException;

    @DELETE
    @Path("{id}")
    public void requestRemoveAuthorById(@PathParam("id") String id) throws BookshopException;


    @OPTIONS
    public Response requestOptions();
}
