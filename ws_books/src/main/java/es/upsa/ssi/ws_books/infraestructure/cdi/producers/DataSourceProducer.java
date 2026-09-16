package es.upsa.ssi.ws_books.infraestructure.cdi.producers;

import es.upsa.ssi.common.datasource.Utils;
import es.upsa.ssi.common.qualifiers.CustomDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;

import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceProducer {

    @Inject
    private Config config;

    @Produces
    @CustomDataSource
    @Dependent
    public DataSource produceDataSource(InjectionPoint injectionPoint) {
        CustomDataSource customDataSource = Utils.findQualifier(injectionPoint, CustomDataSource.class, CustomDataSource.Literal.DEFAULT);
        String dataSourceName = customDataSource.value().trim();
        return Utils.createDataSource(config, dataSourceName);
    }

}