package id.web.sukenda.system.handler;

import id.web.sukenda.entity.Role;
import id.web.sukenda.repository.RoleRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class RoleHandler {

    private final RoleRepository roleRepository;

    public RoleHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.findAll(), Role.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.findById(id), Role.class);
    }

    public Mono<ServerResponse> findByCode(ServerRequest request) {
        String code = request.pathVariable("code");

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleRepository.findFirstByCode(code), Role.class);
    }

}
