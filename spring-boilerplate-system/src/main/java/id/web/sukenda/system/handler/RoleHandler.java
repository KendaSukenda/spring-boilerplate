package id.web.sukenda.system.handler;

import id.web.sukenda.dto.RoleDto;
import id.web.sukenda.entity.Role;
import id.web.sukenda.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.status;

@Component
@Slf4j
public class RoleHandler {

    private final RoleService roleService;

    public RoleHandler(RoleService roleService) {
        this.roleService = roleService;
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        log.info("Path {} ", request.path());

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleService.findAll(), Role.class);
    }

    public Mono<ServerResponse> findByCode(ServerRequest request) {
        String code = request.pathVariable("code");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleService.findByCode(code), Role.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<RoleDto> roleDtoMono = request.bodyToMono(RoleDto.class);
        Mono<Role> roleMono = roleDtoMono.flatMap(roleService::save);

        return roleMono.flatMap(role -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(role))
                .onErrorResume(throwable -> status(HttpStatus.BAD_REQUEST).build());
    }

}
