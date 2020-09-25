package id.web.sukenda.system.router;

import id.web.sukenda.system.handler.RoleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RoleRouter {

    @Bean("RoleRouter")
    public RouterFunction<ServerResponse> route(RoleHandler roleHandler) {
        return RouterFunctions
                .route(GET("/roles").and(accept(MediaType.APPLICATION_JSON)), roleHandler::find)
                .andRoute(POST("/roles").and(accept(MediaType.APPLICATION_JSON)), roleHandler::save)
                .andRoute(GET("/roles/{code}").and(accept(MediaType.APPLICATION_JSON)), roleHandler::findByCode);
    }

}
