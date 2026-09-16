package es.upsa.ssi.bookshop.gateway.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;


@OpenAPIDefinition(info = @Info(
        title = "Servicio GATEWAY",
        version = "1.0.0",
        contact = @Contact(
                name = "Alfonzo Brando Silva Capellan",
                email = "absilvaca.inf@upsa.es",
                url = "https://www.upsa.es/")
)
)
@ApplicationScoped
@QuarkusMain
@ApplicationPath("/")
public class GatewayApplication extends Application {
    public static void main(String... args) {
        Quarkus.run(args);
    }
}
