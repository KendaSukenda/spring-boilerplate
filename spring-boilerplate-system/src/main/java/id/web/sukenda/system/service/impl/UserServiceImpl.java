package id.web.sukenda.system.service.impl;

import id.web.sukenda.common.exception.InvalidUsernamePasswordException;
import id.web.sukenda.common.exception.UserAlreadyExistException;
import id.web.sukenda.common.utils.DTOUtils;
import id.web.sukenda.dto.UserDto;
import id.web.sukenda.entity.User;
import id.web.sukenda.repository.UserRepository;
import id.web.sukenda.system.security.JWTTokenProvider;
import id.web.sukenda.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JWTTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Flux<User> find() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> doRegister(UserDto param) {
        Mono<User> userMono = userRepository.findByUsername(param.getUsername());
        return userMono
                .defaultIfEmpty((User) DTOUtils.convertToEntity(new User(), param))
                .flatMap(user -> {
                    if (user.getId() == null) {
                        user.setEnabled(true);
                        user.setPassword(passwordEncoder.encode(param.getPassword()));
                        user.setAccessToken(tokenProvider.generateToken(user));
                        return userRepository.insert(user).flatMap(Mono::just);

                    } else {
                        return Mono.error(new UserAlreadyExistException("User sudah ada, silahkan menggunakan user lain"));
                    }
                });
    }

    @Override
    public Mono<User> doLogin(UserDto param) {
        return userRepository.findByUsername(param.getUsername())
                .switchIfEmpty(Mono.error(new InvalidUsernamePasswordException("Pastikan username dan password anda bener")))
                .flatMap((user -> {
                    if (passwordEncoder.matches(param.getPassword(), user.getPassword())) {
                        user.setAccessToken(tokenProvider.generateToken(user));

                        return Mono.just(user);
                    }

                    return Mono.error(new InvalidUsernamePasswordException("Pastikan username dan password anda bener"));
                }));
    }
}
