package es.upsa.ssi.ws_authors.application.rest.resources;

import es.upsa.ssi.common.dtos.UnidentifiedAuthor;
import es.upsa.ssi.common.entity.Author;
import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.common.mappers.Mapper;
import es.upsa.ssi.ws_authors.application.adapters.usecases.Service;
import es.upsa.ssi.ws_authors.application.rest.resources.beans.ErrorMessage;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.health.Liveness;
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
@Path("/authors")
public class AuthorsResource {

    @Inject
    Service service;

    @Context
    UriInfo uriInfo;


    @Operation(operationId = "getAuthors",
            summary = "Consulta los registros Authors",
            description = "Devuelve una lista con todos los registros Authors."
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Devuelve una lista con todos los registros Authors.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY,
                                    implementation = Author.class
                            )
                    )
            ),
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthors() throws BookshopException {
        return Response.ok(service.getAuthors()).build();
    }



    @Operation(operationId = "getAuthor",
            summary = "Consulta un registro Author",
            description = "Devuelve un registro Author identificado por ID"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Registro Author localizado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Author.class
                            )
                    )
            ),
            @APIResponse(responseCode = "404",
                    description = "Registro Author no localizado."
            )
    })
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@Parameter(required = true,
                                        description = "ID Author",
                                        in = ParameterIn.PATH,
                                        name = "ID",
                                        schema = @Schema(type = SchemaType.STRING)
    )@PathParam("id") String id) throws BookshopException {
        return service.getAuthor(id)
                .map(author -> Response.ok(author).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }




    @Operation(operationId = "createAuthor",
            summary = "Registrar un nuevo Author.",
            description = "Registrar un nuevo Author"
    )
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Author creado correctamente.",
                    headers = @Header(
                            name = HttpHeaders.LOCATION,
                            description = "URI del Author creado.",
                            schema = @Schema(
                                    type = SchemaType.STRING,
                                    format = "uri"
                            )
                    ),
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Author.class
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
    public Response createAuthor(@RequestBody(
                                            description = "Datos del Author",
                                            required = true,
                                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                                    schema = @Schema(type = SchemaType.OBJECT,
                                                            implementation = UnidentifiedAuthor.class
                                                    )
                                            )
    ) UnidentifiedAuthor unidentifiedAuthor) throws BookshopException {
        Mapper mapper = new Mapper();
        Author author = service.addAuthor(mapper.toAuthor(unidentifiedAuthor));

        URI uri = uriInfo.getBaseUriBuilder()
                .path("authors/{id}")
                .build(author.id());

        return Response.created(uri).entity(author).build();
    }




    @Operation(operationId = "updateAuthor",
            description = "Actualizar un author.",
            summary = "Actualiza un Author localizado por su ID."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Author actualizado correctamente",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    type = SchemaType.OBJECT,
                                    implementation = Author.class
                            )
                    )
            ),

            @APIResponse(
                    responseCode = "404",
                    description = "Registro Author no localizado."
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
    public Response updateAuthor(@Parameter(required = true,
                                            description = "ID Author",
                                            in = ParameterIn.PATH,
                                            name = "ID",
                                            schema = @Schema(type = SchemaType.STRING)
    )@PathParam("id") String id, @RequestBody(
            description = "Datos del Author",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT,
                            implementation = UnidentifiedAuthor.class
                    )
            )
    ) UnidentifiedAuthor unidentifiedAuthor) throws BookshopException {
        Mapper mapper = new Mapper();
        Author author = mapper.toAuthor(unidentifiedAuthor).withId(id);
        service.updateAuthor(author);
        return Response.ok(author).build();
    }



    @Operation(
            operationId = "deleteAuthor",
            description = "Elimina un Author",
            summary = "Elimina un Author identificado por su ID."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Author eliminado correctamente"
            ),

            @APIResponse(
                    responseCode="404",
                    description="Registro Author no localizado."
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteAuthor(@Parameter(required = true,
            description = "ID Author",
            in = ParameterIn.PATH,
            name = "ID",
            schema = @Schema(type = SchemaType.STRING)
    ) @PathParam("id") String id) throws BookshopException {
        service.deleteAuthor(id);
        return Response.ok().build();
    }
}
