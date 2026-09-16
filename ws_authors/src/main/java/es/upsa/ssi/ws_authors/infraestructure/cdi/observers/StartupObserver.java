package es.upsa.ssi.ws_authors.infraestructure.cdi.observers;

import es.upsa.ssi.common.mpconfig.CustomFlywayProperties;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.flywaydb.core.Flyway;


@ApplicationScoped
public class StartupObserver {

    @Inject
    @ConfigProperties
    CustomFlywayProperties customFlywayProperties;

    @Inject
    Flyway flyway;

    public void observesStart(@Observes Startup startupEvent) {
        if ( customFlywayProperties.isCleanAtStart() ) {
            flyway.clean();
        }

        if ( customFlywayProperties.isMigrateAtStart() ) {
            flyway.migrate();
        }
    }
}
