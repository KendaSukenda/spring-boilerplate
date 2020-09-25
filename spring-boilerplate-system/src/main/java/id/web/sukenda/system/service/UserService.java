package id.web.sukenda.system.service;

import id.web.sukenda.dto.UserDto;
import id.web.sukenda.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> findByUsername(String username);

    Flux<User> find(int page, int size);

    Mono<User> findById(String id);

    Mono<User> register(UserDto param);

    Mono<User> login(UserDto param);

    Mono<User> doRefreshToken(String refreshToken);

}
