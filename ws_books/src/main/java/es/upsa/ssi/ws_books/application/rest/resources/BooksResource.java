package es.upsa.ssi.ws_books.application.rest.resources;

import es.upsa.ssi.common.dtos.UnidentifiedBook;
import es.upsa.ssi.common.entity.Book;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.common.mappers.Mapper;
import es.upsa.ssi.ws_books.application.adapters.usecases.Service;
import es.upsa.ssi.ws_books.application.rest.resources.beans.ErrorMessage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.net.URI;

@RequestScoped
@Path("/books")
public class BooksResource {

    @Inject
    Service service;

    @Context
    UriInfo uriInfo;

    @Operation(operationId = "getBooks",
            summary = "Consulta los registros Books",
            description = "Devuelve una lista con todos los registros Books."
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Devuelve una lista con todos los registros Books.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY,
                                    implementation = Book.class
                            )
                    )
            ),
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() throws BookshopException {
        return Response.ok(service.getBooks()).build();
    }



    @Operation(operationId = "getBook",
            summary = "Consulta un registro Book",
            description = "Devuelve un registro Book identificado por ID"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Registro Book localizado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Book.class
                            )
                    )
            ),
            @APIResponse(responseCode = "404",
                    description = "Registro Book no localizado."
            )
    })
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@Parameter(required = true,
                                        description = "ID Book",
                                        in = ParameterIn.PATH,
                                        name = "ID",
                                        schema = @Schema(type = SchemaType.STRING)
    ) @PathParam("id") String id) throws BookshopException {
        return service.getBook(id)
                .map(book -> Response.ok(book).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }



    @Operation(operationId = "createBook",
            summary = "Registrar un nuevo Book.",
            description = "Registrar un nuevo Book"
    )
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Book creado correctamente.",
                    headers = @Header(
                            name = HttpHeaders.LOCATION,
                            description = "URI del Book creado.",
                            schema = @Schema(
                                    type = SchemaType.STRING,
                                    format = "uri"
                            )
                    ),
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Book.class
                            )
                    )
            ),
            @APIResponse(responseCode = "500",
                    description = "Se ha producido un error",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = ErrorMessage.class
                            )
                    )
            )
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(@RequestBody(
            description = "Datos del Book",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT,
                            implementation = UnidentifiedBook.class
                    )
            )
    ) UnidentifiedBook unidentifiedBook) throws BookshopException {
        Mapper mapper = new Mapper();
        Book book = service.addBook(mapper.toBook(unidentifiedBook));

        URI uri = uriInfo.getBaseUriBuilder()
                .path("books/{id}")
                .build(book.id());

        return Response.created(uri).entity(book).build();
    }


    @Operation(operationId = "updateBook",
            description = "Actualizar un Book.",
            summary = "Actualiza un Book localizado por su ID."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Book actualizado correctamente",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    type = SchemaType.OBJECT,
                                    implementation = Book.class
                            )
                    )
            ),

            @APIResponse(
                    responseCode = "404",
                    description = "Registro Book no localizado."
            ),

            @APIResponse(responseCode = "500",
                    description = "Se ha producido un error",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = ErrorMessage.class
                            )
                    )
            )
    })
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@Parameter(required = true,
            description = "ID Book",
            in = ParameterIn.PATH,
            name = "ID",
            schema = @Schema(type = SchemaType.STRING)
    ) @PathParam("id") String id, @RequestBody (
            description = "Datos del Book",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT,
                            implementation = UnidentifiedBook.class
                    )
            )
    ) UnidentifiedBook unidentifiedBook) throws BookshopException {
        Mapper mapper = new Mapper();
        Book book = mapper.toBook(unidentifiedBook).withId(id);
        service.updateBook(book);
        return Response.ok(book).build();
    }


    @Operation(
            operationId = "deleteBook",
            description = "Elimina un Book",
            summary = "Elimina un Book identificado por su ID."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Book eliminado correctamente"
            ),

            @APIResponse(
                    responseCode="404",
                    description="Registro Book no localizado."
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") String id) throws BookshopException {
        service.deleteBook(id);
        return Response.ok().build();
    }




}
