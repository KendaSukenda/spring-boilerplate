package id.web.sukenda.system.service.impl;

import id.web.sukenda.entity.User;
import id.web.sukenda.repository.UserRepository;
import id.web.sukenda.system.security.CustomPasswordEncoder;
import id.web.sukenda.system.security.JWTTokenProvider;
import id.web.sukenda.system.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JWTTokenProvider tokenProvider;

    private final CustomPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTTokenProvider tokenProvider, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> doRegister(User param) {
        param.setEnabled(true);
        param.setPassword(passwordEncoder.encode(param.getPassword()));

        return userRepository.insert(param);
    }

    @Override
    public Mono<User> doLogin(User param) {
        return userRepository.findByUsername(param.getUsername()).doOnSuccess((user -> {
            if (user != null)
                if (passwordEncoder.encode(param.getPassword()).equals(user.getPassword()))
                    user.setAccessToken(tokenProvider.generateToken(user));
        }));
    }
}
