package id.web.sukenda.system.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import id.web.sukenda.system.handler.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean("UserRouter")
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions
                .route(POST("/login").and(accept(MediaType.APPLICATION_JSON)), handler::login)
                .andRoute(POST("/register").and(accept(MediaType.APPLICATION_JSON)), handler::register)
                .andRoute(POST("/refresh-token").and(accept(MediaType.APPLICATION_JSON)), handler::refreshToken)
                .andRoute(GET("/users").and(accept(MediaType.APPLICATION_JSON)), handler::find)
                .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById);
    }
}
