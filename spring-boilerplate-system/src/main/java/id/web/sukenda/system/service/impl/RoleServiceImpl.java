package id.web.sukenda.system.service.impl;

import id.web.sukenda.common.exception.DefaultException;
import id.web.sukenda.common.utils.DTOUtils;
import id.web.sukenda.dto.RoleDto;
import id.web.sukenda.entity.Role;
import id.web.sukenda.repository.RoleRepository;
import id.web.sukenda.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Mono<Role> save(RoleDto param) {
        Mono<Role> mono = roleRepository.findFirstByCode(param.getCode());
        return mono.defaultIfEmpty((Role) DTOUtils.convertToEntity(new Role(), param))
                .flatMap(role -> {
                    if (role.getId() == null) {
                        return roleRepository.insert(role).flatMap(Mono::just);
                    } else {
                        return Mono.error(new DefaultException(HttpStatus.BAD_REQUEST, "Role sudah ada, silahkan menggunakan role lain"));
                    }
                });
    }

    @Override
    public Mono<Role> findByCode(String code) {
        return roleRepository.findFirstByCode(code);
    }

    @Override
    public Mono<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Flux<Role> findAll() {
        return roleRepository.findAll();
    }
}
