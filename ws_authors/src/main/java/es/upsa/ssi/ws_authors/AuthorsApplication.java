package es.upsa.ssi.ws_authors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;


@OpenAPIDefinition(info = @Info(
        title = "Servicio WS_AUTHORS",
        version = "1.0.0",
        contact = @Contact(
                name = "Alfonzo Brando Silva Capellan",
                email = "absilvaca.inf@upsa.es",
                url = "https://www.upsa.es/")
                )
)
@ApplicationPath("/")
@ApplicationScoped
public class AuthorsApplication extends Application {
}
