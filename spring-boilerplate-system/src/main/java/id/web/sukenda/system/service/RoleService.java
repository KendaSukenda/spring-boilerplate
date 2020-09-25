package id.web.sukenda.system.service;

import id.web.sukenda.dto.RoleDto;
import id.web.sukenda.entity.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {

    Mono<Role> save(RoleDto param);

    Mono<Role> findByCode(String code);

    Mono<Role> findById(String id);

    Flux<Role> findAll();

}
