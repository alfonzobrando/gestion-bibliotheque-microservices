package es.upsa.ssi.common.mpconfig;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Data
public class CustomConfigHealth {

    @Inject
    @ConfigProperty(name = "health.config.ready")
    boolean ready;

    @Inject
    @ConfigProperty(name = "health.config.live")
    boolean live;

    @Inject
    @ConfigProperty(name = "HOSTNAME", defaultValue = "desconocido")
    String hostname;

}
