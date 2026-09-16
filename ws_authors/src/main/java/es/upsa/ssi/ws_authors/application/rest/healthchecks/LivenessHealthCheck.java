package es.upsa.ssi.ws_authors.application.rest.healthchecks;

import es.upsa.ssi.common.mpconfig.CustomConfigHealth;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

    @Inject
    CustomConfigHealth customConfigHealth;

    // http://localhost:8081/health/live

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("Live")
                .status(customConfigHealth.isLive())
                .withData("live", customConfigHealth.isLive())
                .withData("ready", customConfigHealth.isReady())
                .build();
    }
}
