package id.web.sukenda.system.handler;

import id.web.sukenda.dto.UserDto;
import id.web.sukenda.entity.User;
import id.web.sukenda.system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> doLogin(ServerRequest request) {
        Mono<User> result = request.bodyToMono(UserDto.class)
                .flatMap(userService::doLogin);

        return result.flatMap(user -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user))
                .onErrorResume(throwable -> status(HttpStatus.BAD_REQUEST).build());
    }

    public Mono<ServerResponse> doRegister(ServerRequest request) {
        Mono<UserDto> userDtoMono = request.bodyToMono(UserDto.class);
        Mono<User> userMono = userDtoMono.flatMap(userService::doRegister);

        return userMono.flatMap(user -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user))
                .onErrorResume(throwable -> status(HttpStatus.BAD_REQUEST).build());
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        String page = request.queryParam("page").orElse("0");
        String size = request.queryParam("size").orElse("10");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.find(Integer.parseInt(page), Integer.parseInt(size)), User.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findById(id), User.class);
    }

}
