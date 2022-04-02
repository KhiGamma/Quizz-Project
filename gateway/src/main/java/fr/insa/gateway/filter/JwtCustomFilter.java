package fr.insa.gateway.filter;

import fr.insa.gateway.dto.UserResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class JwtCustomFilter extends AbstractGatewayFilterFactory<JwtCustomFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    public JwtCustomFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(exchange.getRequest().getPath().elements().stream().anyMatch(elem -> elem.value().equals("register") || elem.value().equals("login"))) {
                return chain.filter(exchange);
            }

            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Not Authorized!");
            }

            String jwtHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = jwtHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                return onError(exchange, "Incorrect token structure!");
            }

            return webClientBuilder.build()
                    .get()
                    .uri("http://user-service/user-managment/authorization/is-authorized")
                    .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                    .retrieve().bodyToMono(UserResponse.class)
                    .map(userResponse -> {
                        exchange.getRequest()
                                .mutate()
                                .header("x-auth-user-name", userResponse.getUsername());
                        return exchange;
                    }).flatMap(chain::filter);
        });
    }

    // https://spring.io/reactive
    private Mono<Void> onError(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

}
