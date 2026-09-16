package es.upsa.ssi.common.mpconfig;


import es.upsa.ssi.common.qualifiers.CustomDataSource;
import jakarta.enterprise.context.Dependent;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "local.flyway")
@Dependent
@Data
public class CustomFlywayProperties {
    @ConfigProperty(name = "datasource-name", defaultValue = CustomDataSource.DEFAULT_VALUE)
    private String dataSourceName;

    @ConfigProperty(name = "clean-at-start", defaultValue = "true")
    private boolean cleanAtStart;

    @ConfigProperty(name = "migrate-at-start", defaultValue = "true")
    private boolean migrateAtStart;
}
