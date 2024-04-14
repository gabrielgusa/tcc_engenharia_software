package br.com.findfoodtosave.gateway.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.gateway.config.AuthenticationFilter;

@Component
public class GatewayRoutes {
    
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Value("${back-end.api-user.uri}")
    private String apiUserUri;

    @Value("${back-end.api-store.uri}")
    private String apiStoreUri;

    @Value("${back-end.api-product.uri}")
    private String apiProductUri;
    
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("no-auth-service", r -> r.path("/api/v1/users")
                    .or().path("/api/v1/login")
                    .uri(apiUserUri))
                .route("auth-service", r-> r.path("/api/v1/users/me")
                    .filters(f -> f.filter(this.authenticationFilter))
                    .uri(apiUserUri))
                .route("stores-service", r-> r.path("/api/v1/users/*/stores")
                    .or().path("/api/v1/stores*").or().path("/api/v1/users/*/stores/*")
                    .filters(f -> f.filter(this.authenticationFilter))
                    .uri(apiStoreUri))
                .route("products-service", r-> r.path("/api/v1/users/*/stores/*/products/**")
                    .or().path("/api/v1/stores/*/products")
                    .filters(f -> f.filter(this.authenticationFilter))
                    .uri(apiProductUri))
                .build();
    }

}
