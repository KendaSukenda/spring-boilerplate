package id.web.sukenda.system.service;

import id.web.sukenda.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> findByUsername(String username);

    Mono<User> findById(String id);

    Mono<User> doRegister(User param);

    Mono<User> doLogin(User param);

}
