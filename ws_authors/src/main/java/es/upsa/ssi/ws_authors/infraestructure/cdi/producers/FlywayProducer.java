package es.upsa.ssi.ws_authors.infraestructure.cdi.producers;

import es.upsa.ssi.common.qualifiers.CustomDataSource;
import es.upsa.ssi.common.mpconfig.LocationsFlywayProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import javax.sql.DataSource;

@ApplicationScoped
public class FlywayProducer {

    @Inject
    @CustomDataSource
    DataSource dataSource;


    @Inject
    @ConfigProperties
    LocationsFlywayProperty locationsFlywayProperty;

    @Produces
    @Dependent
    public Flyway createFlyway() {
        FluentConfiguration configuration = Flyway.configure();
        configuration.dataSource(dataSource)
//                .locations("db/migration") // Valor por defecto de Flyway
                .locations(locationsFlywayProperty.getLocations())
                .cleanDisabled(false)
                .validateOnMigrate(false); // Nota 1*
        return configuration.load();
    }
}

/**
 * NOTA 1:
 * Esa linea la agrego porque al ejecutar este módulo (ws_books) sucede que Flyway
 * me daba un error de que hay una migración (versión 1.0.0) que está
 * registrada como aplicada en la BD, pero Flyway no la encontraba en db/migration.
 * Esa versión del fichero corresponde al otro módulo (ws_authors)
 */