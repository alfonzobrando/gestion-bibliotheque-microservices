package es.upsa.ssi.common.mpconfig;

import jakarta.enterprise.context.Dependent;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "local.flyway")
@Dependent
@Data
public class LocationsFlywayProperty {

    @ConfigProperty(name = "locations", defaultValue = "db/migration")
    private String[] locations;
}
