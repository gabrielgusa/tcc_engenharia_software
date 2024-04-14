package br.com.findfoodtosave.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import br.com.findfoodtosave.gateway.client.UserClient;
import feign.FeignException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private UserClient userClient;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (this.isAuthMissing(request)) {
            return this.onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        final String token = this.getAuthHeader(request);

        try {
            userClient.getUser(token);
        } catch(FeignException fx) {
            return this.onError(exchange, HttpStatus.valueOf(fx.status()), fx);
        }

        return chain.filter(exchange);
    }
    
    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
    
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, FeignException fx) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add("Content-Type", "application/json");
        response.getHeaders().add("transfer-encoding", "chunked");
        var contentResponse = response.bufferFactory().wrap(fx.contentUTF8().getBytes());
        return response.writeWith(Flux.just(contentResponse));
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

}
