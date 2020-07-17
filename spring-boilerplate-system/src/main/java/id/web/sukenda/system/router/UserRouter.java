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

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions
                .route(POST("/do-login").and(accept(MediaType.APPLICATION_JSON)), handler::doLogin)
                .andRoute(POST("/do-register").and(accept(MediaType.APPLICATION_JSON)), handler::doRegister)
                .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById);
    }
}
